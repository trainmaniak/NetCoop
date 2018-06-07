package cz.netcoop;

import cz.netcoop.abilities.IAbility;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Message {
    public enum Type {
        REQUEST,
        REPLY
    }

    private Type type;
    private IAbility ability;
    private Address address;

    public Type getType() {
        return type;
    }

    public Address getAddress() {
        return address;
    }

    public Message(Type type) {
        this.type = type;
        // TODO ability
        this.address = AppNetCoop.getApp().getConnector().getMyAddress();
    }

    public Message(Type type, IAbility ability, Address address) {
        this.type = type;
        this.ability = ability;
        this.address = address;
    }

    @Override
    public String toString() {
        return "type=" + type.toString() +
                ", ncAddr=" + address.getNcAddressString() +
                ", ipAddr=" + address.getIpAddressString();
    }

    public static class Parser {
        public static byte[] build(Message message) {
            byte[] result = new byte[6];

            result[0] = (byte)message.type.ordinal();
            result[1] = message.address.getNcAddress();

            int i = 2;
            for (byte octet : message.address.getIpAddressByte()) {
                result[i++] = octet;
            }

            // TODO message.ability.request();

            return result;
        }

        public static Message parse(byte[] message) {
            Type type = Type.values()[message[0]];

            byte ncAddr = message[1];
            byte[] ipAddr = new byte[4];

            int i = 0;
            for (int j = 2; j < message.length; j++) {
                ipAddr[i++] = message[j];
            }

            try {
                return new Message(type, new Address(ncAddr, InetAddress.getByAddress(ipAddr)));
            } catch (UnknownHostException e) {
                DebugPrinter.print("parsing another ip address", "failed - bad format");
                e.printStackTrace();

                return null;
            }
        }
    }
}
