package cz.netcoop;

import java.util.ArrayList;
import java.util.List;

public class Router {
    //private List<IDevice> deviceList;

    private List<Message> messageBuffer = new ArrayList<>();

    /*
    public Router(List<IDevice> deviceList) {
        this.deviceList = deviceList;
    }*/

    /*
    public IDevice getDestinationDevice(Message message) {
        return message.destination;
    }
    */

    public void bufferAdd(Message message) {
        messageBuffer.add(message);
    }

    public Message bufferGet(Session caller) {
        for (Message message : messageBuffer) {
            if (message.destination == caller.getDevice()) {
                messageBuffer.remove(message);
                return message;
            }
        }

        return null;
    }
}
