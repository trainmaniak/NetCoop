package cz.netcoop;

import cz.netcoop.Abilities.IAbility;
import cz.netcoop.Connectors.Connector;
import cz.netcoop.ServingDaemon.Beacon;

import java.util.ArrayList;
import java.util.List;

public class AppNetCoop implements IAppNetCoop {
    private List<IDevice> deviceList = new ArrayList<>();
    private List<IAbility> abilityList = new ArrayList<>();
    private Connector connector = new Connector(deviceList, abilityList);

    private Beacon beacon = new Beacon(this);

    public List<IDevice> getDeviceList() {
        return deviceList;
    }

    public List<IAbility> getAbilityList() {
        return abilityList;
    }

    public Connector getConnector() {
        return connector;
    }

    public AppNetCoop() {
    }

    public void start() {
        DebugPrinter.print("App start", "ok");
        //explorer.start();
        beacon.start();

        DebugPrinter.print("Threat test", "ok");
    }
}
