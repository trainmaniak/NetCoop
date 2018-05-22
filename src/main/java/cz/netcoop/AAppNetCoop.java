package cz.netcoop;

import cz.netcoop.Abilities.IAbility;

import java.util.ArrayList;
import java.util.List;

public abstract class AAppNetCoop implements IAppNetCoop {
    protected List<IDevice> deviceList = new ArrayList<>();
    protected List<IAbility> abilityList = new ArrayList<>();

    protected Connector connector;

    public List<IDevice> getDeviceList() {
        return deviceList;
    }

    public List<IAbility> getAbilityList() {
        return abilityList;
    }

    public Connector getConnector() {
        return connector;
    }

    public AAppNetCoop() {
        connector = new Connector(deviceList, abilityList);
    }
}
