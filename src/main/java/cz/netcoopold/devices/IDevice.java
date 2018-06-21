package cz.netcoopold.devices;

import cz.netcoopold.Address;

public interface IDevice {
    Address getAddress();
    String getHostName();
}
