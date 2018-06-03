package cz.netcoop;

import cz.netcoop.Session;
import cz.netcoop.devices.IDevice;
import cz.netcoop.servingdaemon.AServeDaemon;

public class ClientHandler extends AAppNetCoopObjectThread {
    private IDevice device;
    private Session session;

    public IDevice getDevice() {
        return device;
    }

    public Session getSession() {
        return session;
    }

    public ClientHandler(IDevice device) {
        this.device = device;
    }

    @Override
    public void run() {

        // TODO create conn

        // TODO prijmout message a pres router odeslat

        DebugPrinter.print("test of creating new connection", device.toString());
    }
}
