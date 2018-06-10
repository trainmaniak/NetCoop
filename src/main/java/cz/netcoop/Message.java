package cz.netcoop;

import cz.netcoop.abilities.IAbility;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Message {
    public enum Type {
        REQUEST,
        REPLY
    }

    private Address destination;
    private Address source;
    private Type type;
    private IAbility ability;
    private byte[] data;

    public Address getDestination() {
        return destination;
    }

    public Address getSource() {
        return source;
    }

    public Type getType() {
        return type;
    }

    public IAbility getAbility() {
        return ability;
    }

    public Message(Address source, Type type, IAbility ability) {
        this.destination = AppNetCoop.getApp().getConnector().getBroadcastAddress();
        this.source = source;
        this.type = type;
        this.ability = ability;
    }

    public Message(Address destination, Address source, Type type, IAbility ability) {
        this.destination = destination;
        this.source = source;
        this.type = type;
        this.ability = ability;
    }

    @Override
    public String toString() {
        return "type=" + type.toString() +
                ", ncAddr=" + destination.getNcAddressString() +
                ", ipAddr=" + destination.getIpAddressString();
    }

    public static class Parser {
        public static byte[] build(Message message) {
            byte[] result = new byte[256];

            result[0] = message.destination.getNcAddress();
            result[1] = message.source.getNcAddress();
            result[2] = (byte)message.type.ordinal();
            result[3] = message.ability.getId();

            byte[] request = message.ability.request();
            System.arraycopy(request, 0, result, 4, request.length);

            return result;
        }

        public static Message parse(byte[] message) {
            AppNetCoop app = AppNetCoop.getApp();

            Address destination = app.getDevice(message[0]).getAddress();
            Address source = app.getDevice(message[1]).getAddress();
            Type type = Type.values()[message[2]];
            IAbility ability = app.getAbility(message[3]);

            byte[] data = new byte[message.length - 4];
            System.arraycopy(data, 0, message, 4, message.length);

            Message newMessage = new Message()


            byte ncAddr = message[1];
            byte[] ipAddr = new byte[4];

            int i = 0;
            for (int j = 2; j < message.length; j++) {
                ipAddr[i++] = message[j];
            }

            try {
                return new Message(type, null, new Address(ncAddr, InetAddress.getByAddress(ipAddr)));
            } catch (UnknownHostException e) {
                DebugPrinter.print("parsing another ip destination", "failed - bad format");
                e.printStackTrace();

                return null;
            }
        }
    }
}
