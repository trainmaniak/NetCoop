package cz.netcoop;

import java.util.List;

public class Router {
    private List<IDevice> deviceList;

    public Router(List<IDevice> deviceList) {
        this.deviceList = deviceList;
    }

    public IDevice getDestinationDevice(Message message) {
        return message.destination;
    }
}
