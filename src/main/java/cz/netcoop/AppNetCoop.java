package cz.netcoop;

import cz.netcoop.abilities.IAbility;
import cz.netcoop.devices.CommonDevice;
import cz.netcoop.devices.IDevice;

import java.util.ArrayList;
import java.util.List;

public class AppNetCoop {
    private List<IDevice> deviceList = new ArrayList<>(); // TODO iterable
    private List<IAbility> abilityList = new ArrayList<>();

    private Connector connector = new Connector();
    private ConnectorsSecretary connectorsSecretary = new ConnectorsSecretary();

    // TODO device ma jiny charakter jak ability, ability jsou staticke, dev jsou ex instance, blbost

    private static AppNetCoop app = new AppNetCoop();

    public static AppNetCoop getApp() {
        return app;
    }

    public List<IDevice> getDeviceList() {
        return deviceList;
    }

    public IDevice getDevice(byte ncAddress) {
        for (IDevice dev : getDeviceList()) {
            if (dev.getAddress().getNcAddress() == ncAddress) {
                return dev;
            }
        }

        return null;
    }

    public IDevice addDevice(IDevice device) {
        if (getDevice(device.getAddress().getNcAddress()) != null) {
            return device;
        }

        deviceList.add(device);

        // print
        StringBuilder print = new StringBuilder();
        for (IDevice dev : deviceList) {
            print.append(dev.toString()).append("\n");
        }
        DebugPrinter.print("devices", print.toString());

        return device;
    }

    public IDevice getAddDevice(Address address) {
        IDevice dev = getDevice(address.getNcAddress());

        if (dev != null) {
            return dev;
        }

        return addDevice(new CommonDevice(ncAddress));

        if (!addNew) {
            return null; // TODO neresim null, muze to byt problem
        }

        return addDevice();
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

    public ConnectorsSecretary getConnectorsSecretary() {
        return connectorsSecretary;
    }

    private AppNetCoop() {
    }

    public void start() {
        DebugPrinter.print("App start", "ok");
        connector.start(); // TODO proc nove vlakno ??? zatim neni potreba
        DebugPrinter.print("Threat test", "ok");
    }
}
