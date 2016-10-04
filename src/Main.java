import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    //variables for storing initial position of the stage at the beginning of drag
    private double initX;
    private double initY;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 1024, 600));

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                initX = me.getScreenX() - primaryStage.getX();
                initY = me.getScreenY() - primaryStage.getY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                primaryStage.setX(me.getScreenX() - initX);
                primaryStage.setY(me.getScreenY() - initY);
            }
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
