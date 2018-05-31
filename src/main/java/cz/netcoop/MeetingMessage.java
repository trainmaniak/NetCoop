package cz.netcoop;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MeetingMessage {
    private MeetingMessageType type;
    private int address;
    private InetAddress netAddress;

    public MeetingMessageType getType() {
        return type;
    }

    public int getAddress() {
        return address;
    }

    public InetAddress getNetAddress() {
        return netAddress;
    }

    private MeetingMessage(MeetingMessageType type, int address, InetAddress netAddress) {
        this.type = type;
        this.address = address;
        this.netAddress = netAddress;
    }

    public String assemble() {
        if (type == MeetingMessageType.QUERY)
            return String.valueOf(type.ordinal());
        else
            return String.valueOf(type.ordinal())
            + String.valueOf(address)
            + String.valueOf(netAddress);
    }

    public static class Builder {
        public static MeetingMessage parseMessage(String message) {
            int typeNum = Integer.parseInt(message.substring(0, 1));
            MeetingMessageType type = MeetingMessageType.values()[typeNum];

            if (type == MeetingMessageType.QUERY) {
                return new MeetingMessage(type, 0, null);
            }

            int address = Integer.parseInt(message.substring(1, 5));
            String netAddress = message.substring(5, message.length());

            try {
                return new MeetingMessage(type, address, InetAddress.getByName(netAddress));
            } catch (UnknownHostException e) {
                DebugPrinter.print("parsing another ip address", "failed - bad format");
                e.printStackTrace();
                return null;
            }
        }

        public static MeetingMessage createQuery() {
            return new MeetingMessage(MeetingMessageType.QUERY, 0, null);
        }

        public static MeetingMessage createReply(int address, InetAddress netAddress) {
            return new MeetingMessage(MeetingMessageType.REPLY, address, netAddress);
        }
    }
}
