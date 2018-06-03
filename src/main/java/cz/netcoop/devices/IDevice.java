package cz.netcoop.devices;

import cz.netcoop.Address;

import java.net.InetAddress;

public interface IDevice {
    Address getAddress();
    String getHostName();
}
