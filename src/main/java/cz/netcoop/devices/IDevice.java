package cz.netcoop.devices;

import java.net.InetAddress;

public interface IDevice {
    int getAddress();
    InetAddress getNetAddress();
}
