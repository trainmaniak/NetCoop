package cz.netcoop;

import cz.netcoop.devices.IDevice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends AAppNetCoopObjectThread {
    private IDevice device;
    private Session session;
    private List<ActionHandler> actionBuff = new ArrayList<>();

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

        // TODO prijmout message, provest a odeslat odpoved

        DebugPrinter.print("test of creating new connection", device.toString());
    }

    public void sendQuery(Message message) {
        Thread t = new Thread(() -> { // TODO proc nove vlakno ??
            try {
                actionBuff.add(new ActionHandler(message.getAbility()));

                getConnector().send(session.getPortMe(), message);
            } catch (IOException e) {
                DebugPrinter.print("send TCP", "failed");
                e.printStackTrace();
            }
        });

        t.start();
    }
}
