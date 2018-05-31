package cz.netcoop;

import java.net.InetAddress;
import java.util.Objects;

public abstract class ADevice implements IDevice {
    private int address;
    private InetAddress netAddress;

    @Override
    public final int getAddress() {
        return address;
    }

    @Override
    public final InetAddress getNetAddress() {
        return netAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ADevice aDevice = (ADevice) o;
        return address == aDevice.address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
