package cz.netcoopold;

import java.net.InetAddress;

public class Address {
    private byte ncAddress;
    private InetAddress ipAddress;

    public byte getNcAddress() {
        return ncAddress;
    }

    public String getNcAddressString() {
        int value = (int)ncAddress;
        if (value < 0) value += 256;

        return String.valueOf(value);
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public byte[] getIpAddressByte() {
        return ipAddress.getAddress();
    }

    public String getIpAddressString() {
        return ipAddress.getHostAddress();
    }

    public Address(byte ncAddress, InetAddress ipAddress) {
        this.ncAddress = ncAddress;
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "Address{"
                + "ncAddr=" + getNcAddressString()
                + ", ipAddr=" + getIpAddressString()
                + "}";
    }
}
