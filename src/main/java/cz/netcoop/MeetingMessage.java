package cz.netcoop;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class MeetingMessage {
    private Type type;
    private Address address;

    public Type getType() {
        return type;
    }

    public Address getAddress() {
        return address;
    }

    public enum Type {
        QUERY,
        REPLY
    }

    public MeetingMessage(Type type) {
        this.type = type;
        this.address = AppNetCoop.getApp().getConnector().getMyAddress();
    }

    private MeetingMessage(Type type, Address address) {
        this.type = type;
        this.address = address;
    }

    @Override
    public String toString() {
        return "type=" + type.toString() +
                ", ncAddr=" + address.getNcAddressString() +
                ", ipAddr=" + address.getIpAddressString();
    }

    public static class Builder {
        public static byte[] build(MeetingMessage message) {
            byte[] result = new byte[6];

            result[0] = (byte)message.type.ordinal();
            result[1] = message.address.getNcAddress();

            int i = 2;
            for (byte octet : message.address.getIpAddressByte()) {
                result[i++] = octet;
            }

            return result;
        }

        public static MeetingMessage parse(byte[] message) {
            Type type = Type.values()[message[0]];

            byte ncAddr = message[1];
            byte[] ipAddr = new byte[4];

            int i = 0;
            for (int j = 2; j < message.length; j++) {
                ipAddr[i++] = message[j];
            }

            try {
                return new MeetingMessage(type, new Address(ncAddr, InetAddress.getByAddress(ipAddr)));
            } catch (UnknownHostException e) {
                DebugPrinter.print("parsing another ip address", "failed - bad format");
                e.printStackTrace();

                return null;
            }
        }
    }
}
