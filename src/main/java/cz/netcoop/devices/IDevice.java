package cz.netcoop.devices;

import cz.netcoop.Address;

public interface IDevice {
    Address getAddress();
    String getHostName();
}
