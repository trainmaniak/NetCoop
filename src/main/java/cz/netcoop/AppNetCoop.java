package cz.netcoop;

import cz.netcoop.Abilities.IAbility;
import cz.netcoop.Connectors.Connector;
import cz.netcoop.ServingDaemon.BeaconDaemon;

import java.util.ArrayList;
import java.util.List;

public class AppNetCoop implements IAppNetCoop {
    private List<IDevice> deviceList = new ArrayList<>();
    private List<IAbility> abilityList = new ArrayList<>();
    private Connector connector = new Connector();

    private BeaconDaemon beaconDaemon = new BeaconDaemon();

    private static AppNetCoop app = new AppNetCoop();

    public static AppNetCoop getApp() {
        return app;
    }

    public List<IDevice> getDeviceList() {
        return deviceList;
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

}
