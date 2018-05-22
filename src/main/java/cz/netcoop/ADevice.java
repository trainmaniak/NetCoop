package cz.netcoop;

import java.util.Objects;

public abstract class ADevice implements IDevice {
    public byte address;

    public byte getAddress() {
        return address;
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
