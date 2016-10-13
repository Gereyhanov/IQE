package SerialCommunications;

/**
 * Created by Rizvan on 05.10.2016.
 * Todo: A class describes the data model of the serialCommunications
 */
public class SerialDataListen {
    private final String data;

    public SerialDataListen(String data) {
        this.data = data;
    }

    public String getMessage() {
        return data;
    }
}
