package cz.netcoop;

import cz.netcoop.abilities.IAbility;
import cz.netcoop.devices.IDevice;
import cz.netcoop.servingdaemon.BeaconDaemon;

import java.util.ArrayList;
import java.util.List;

public class AppNetCoop implements IAppNetCoop {
    private List<ClientHandler> clientHandlerList = new ArrayList<>(); // TODO iterable
    private List<IAbility> abilityList = new ArrayList<>();
    private Connector connector = new Connector();

    // TODO device ma jiny charakter jak ability, ability jsou staticke, dev jsou ex instance, blbost

    private BeaconDaemon beaconDaemon = new BeaconDaemon();

    private static AppNetCoop app = new AppNetCoop();

    public static AppNetCoop getApp() {
        return app;
    }

    public List<ClientHandler> getClientHandlerList() {
        return clientHandlerList;
    }

    public ClientHandler getClientHandler(byte ncAddress) {
        for (ClientHandler ch : clientHandlerList) {
            if (ch.getDevice().getAddress().getNcAddress() == ncAddress) {
                return ch;
            }
        }

        return null;
    }

    public ClientHandler addClientHandler(IDevice device) {
        ClientHandler newCH = new ClientHandler(device);
        clientHandlerList.add(newCH);

        return newCH;
    }

    // TODO iterable !!!

    public ActionHandler getActionHandler(byte ncAddress) {
        return getClientHandler(ncAddress).getLastAction();
    }

    public List<IDevice> getDeviceList() {
        List<IDevice> result = new ArrayList<>(); // TODO iterable ??

        for (ClientHandler ch : clientHandlerList) {
            result.add(ch.getDevice());
        }

        return result;
    }

    public IDevice getDevice(byte ncAddress) {
        for (IDevice dev : getDeviceList()) {
            if (dev.getAddress().getNcAddress() == ncAddress) {
                return dev;
            }
        }

        return null;
    }

    public List<IAbility> getAbilityList() {
        return abilityList;
    }

    public IAbility getAbility(byte abilityID) {
        for (IAbility ability : abilityList) {
            if (ability.getId() == abilityID) {
                return ability;
            }
        }

        return null; // TODO neresim null, muze to byt problem
    }

    public Connector getConnector() {
        return connector;
    }

    public BeaconDaemon getBeaconDaemon() {
        return beaconDaemon;
    }

    private AppNetCoop() {
    }

    public void start() {
        DebugPrinter.print("App start", "ok");
        beaconDaemon.start(); // TODO proc nove vlakno ??? zatim neni potreba
        DebugPrinter.print("Threat test", "ok");
    }

    public void addDevice(IDevice device) {
        if (getDevice(device.getAddress().getNcAddress()) != null) {
            return;
        }

        ClientHandler ch = new ClientHandler(device);
        clientHandlerList.add(ch);
        ch.start();

        // print
        StringBuilder print = new StringBuilder();
        for (IDevice dev : getDeviceList()) {
            print.append(dev.toString()).append("\n");
        }
        DebugPrinter.print("devices", print.toString());
    }
}
