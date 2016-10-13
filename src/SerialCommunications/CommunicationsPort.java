package SerialCommunications;
import jssc.*;
/**
 * Created by Rizvan on 04.10.2016.
 * Todo: Methods for connecting to the com port of the PC, on the basis of the library jssc (the current version at the time of development 2.8.0)
 */
public class CommunicationsPort{
    private String data = null;
    private static SerialPort serialPort;
    private EventBusSerialData eventBusSerialData = new EventBusSerialData();

    public CommunicationsPort() {

    }

    public void openPort (){
        serialPort = new SerialPort("COM6");

        try {
            //Открываем порт
            serialPort.openPort();
            //Выставляем параметры
            serialPort.setParams(SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Включаем аппаратное управление потоком
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            //Устанавливаем ивент лисенер и маску
            serialPort.addEventListener(new CommunicationsPort.PortReader(), SerialPort.MASK_RXCHAR);
            //Отправляем запрос устройству
            //serialPort.writeString("Get data");
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    private class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    data = serialPort.readString(event.getEventValue());
                    EventBusSerialData.eventBus.post(new SerialDataListen(data));
                    //отправка данных
                    //serialPort.writeString("Get data");
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    public String getDataComPort(){
        return data;
    }

}

