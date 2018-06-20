package cz.netcoop.devices;

import cz.netcoop.Address;

import java.util.Objects;

public abstract class ADevice implements IDevice {
    private Address address;
    private String hostName;

    @Override
    public final Address getAddress() {
        return address;
    }

    @Override
    public String getHostName() {
        return hostName;
    }

    ADevice(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ADevice:\n"
                + "\t" + address.toString()
                + "\t" + hostName;
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
