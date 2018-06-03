package cz.netcoop;

import cz.netcoop.abilities.IAbility;
import cz.netcoop.devices.CommonDevice;
import cz.netcoop.devices.IDevice;
import cz.netcoop.servingdaemon.BeaconDaemon;

import java.util.ArrayList;
import java.util.List;

public class AppNetCoop implements IAppNetCoop {
    //private List<IDevice> deviceList = new ArrayList<>();
    private List<ClientHandler> clientHandlerList = new ArrayList<>();
    private List<IAbility> abilityList = new ArrayList<>();
    private Connector connector = new Connector();

    private BeaconDaemon beaconDaemon = new BeaconDaemon();

    private static AppNetCoop app = new AppNetCoop();

    public static AppNetCoop getApp() {
        return app;
    }

    public List<IDevice> getDeviceList() {
        List<IDevice> result = new ArrayList<>();

        for (ClientHandler ch : clientHandlerList) {
            result.add(ch.getDevice());
        }

        return result;
    }

    public List<IAbility> getAbilityList() {
        return abilityList;
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
        beaconDaemon.start();
        DebugPrinter.print("Threat test", "ok");
    }

    public void addDevice(IDevice device) {
        ClientHandler ch = new ClientHandler(device);
        clientHandlerList.add(ch);
        ch.start();

        StringBuilder print = new StringBuilder();
        for (IDevice dev : getDeviceList()) {
            print.append(dev.toString()).append("\n");
        }
        DebugPrinter.print("devices", print.toString());
    }
}
