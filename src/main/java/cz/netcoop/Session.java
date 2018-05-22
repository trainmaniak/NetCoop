package cz.netcoop;

import java.io.IOException;
import java.net.Socket;

public class Session {
    private String ipAddress;
    private IDevice device;
    private Port portOther;
    private Port portMe;

    public String getIpAddress() {
        return ipAddress;
    }

    public Port getPortOther() {
        return portOther;
    }

    public Port getPortMe() {
        return portMe;
    }

    public IDevice getDevice() {
        return device;
    }

    public Session(Socket socketOther, Socket socketMe) throws IOException {
        portOther = new Port(socketOther);
        portMe = new Port(socketMe);
    }

    public Session(String ipAddress, int portNumOther, int portNumMe) throws IOException {
        this.ipAddress = ipAddress;
        portOther = new Port(ipAddress, portNumOther);
        portMe = new Port(ipAddress, portNumMe);
    }
}
