package UI;

import SerialCommunications.SerialDataSingleton;
import SerialCommunications.SerialDataListen;
import com.google.common.eventbus.Subscribe;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.util.Duration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Rizvan on 13.10.2016.
 */
public class Controller {

    private ExecutorService executor;
    private AddToQueue addToQueue;
    private Timeline timeline;
    private SequentialTransition animation;

    int timeRendering = 100;

    Boolean aBooleanConnectButton = true;

    //private String[] dataSerialPort = new String[15];
    private String dataSerialPort = "";


    @FXML
    JFXButton connectButton;
    @FXML
    JFXButton animatedButton;
    @FXML
    JFXTextField selectSerialField;
    @FXML
    JFXTextArea dataTextArea;

    @FXML
    private void initialize() {
        //-- Prepare Executor Services
        executor = Executors.newCachedThreadPool();
        addToQueue = new AddToQueue();
        executor.execute(addToQueue);
        animatedButton.setText("Start render");
        dataTextArea.setText("Ok.");
        dataTextArea.setScrollTop(0.1);
    }

    @FXML
    private void connectEventButton() {

        if (aBooleanConnectButton) {
            connectButton.setText("Stop render");
            prepareTimeline();
            aBooleanConnectButton = false;
        } else {
            connectButton.setText("Start render");
            animation.stop();
            aBooleanConnectButton = true;
        }
    }

    @FXML
    private void animatedEventButton(){
    }


    public void render() {
        dataTextArea.setText(dataTextArea.getText() + "\n" + SerialDataSingleton.getInstance().getData());
    }

    private class AddToQueue extends Thread {
        public void run() {
            try {
                Thread.currentThread().setName(Thread.currentThread().getId() + "-DataAdder");
                Thread.sleep(50);
                executor.execute(addToQueue);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //-- Timeline gets called in the JavaFX Main thread
    private void prepareTimeline() {
        //-- Second slower timeline
        timeline = new Timeline();
        //-- This timeline is indefinite.
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(timeRendering), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //TODO: Вызов функции рендеринга графиков
                        render();
                    }
                })
        );
        //-- Set Animation- Timeline is created now.
        animation = new SequentialTransition();
        animation.getChildren().addAll(timeline);
        animation.play();
    }

    @Subscribe
    public void updateSerialCommunicationsData(SerialDataListen event)   {
        SerialDataSingleton.getInstance().addData(event.getMessage());
    }

}