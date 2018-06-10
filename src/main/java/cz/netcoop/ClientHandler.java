package cz.netcoop;

import cz.netcoop.devices.IDevice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends AAppNetCoopObjectThread {
    private IDevice device;
    private List<ActionHandler> actionBuff = new ArrayList<>();

    public IDevice getDevice() {
        return device;
    }

    public ClientHandler(IDevice device) {
        this.device = device;
    }

    public ActionHandler getLastAction() {
        return actionBuff.get(0);
    }

    public void addAction() {

    }

    @Override
    public void run() {

        // TODO create conn

        // TODO prijmout message, provest a odeslat odpoved

        DebugPrinter.print("test of creating new connection", device.toString());
    }
}
