package cz.netcoopold;

import cz.netcoopold.abilities.Ping;
import cz.netcoopold.devices.IDevice;

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

    public void addAction(byte abilityID, byte[] data) {
        actionBuff.add(new ActionHandler(new Ping(), data));
        // TODO opravit !!! ability se musi najit podle ID
    }

    @Override
    public void run() {

        // TODO create conn

        // TODO prijmout message, provest a odeslat odpoved

        DebugPrinter.print("test of creating new connection", device.toString());
    }
}
