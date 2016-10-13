package SerialCommunications;

import UI.Controller;
import com.google.common.eventbus.EventBus;

/**
 * Created by Rizvan on 13.10.2016.
 * Todo: Class libraries to run on Google Guava(EvenBus) to track events from the seriesCommunications.
 */

public class EventBusSerialData {

    public static final EventBus eventBus = new EventBus();

    public EventBusSerialData() {
        eventBus.register(new Controller());
    }
}
