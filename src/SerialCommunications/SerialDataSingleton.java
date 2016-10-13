package SerialCommunications;

/**
 * Created by Rizvan on 13.10.2016.
 * Todo: Singleton pattern for synchronized data transmission in other classes
 */
public class SerialDataSingleton {
    private static SerialDataSingleton ourInstance = new SerialDataSingleton();
    private static String data = "This is serial data...";
    public static synchronized SerialDataSingleton getInstance() {
        return ourInstance;
    }
    private SerialDataSingleton() {
    }
    public void addData(String inputData){
        data = inputData;
    }
    public String getData(){
        return data;
    }
}
