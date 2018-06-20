package cz.netcoop;

import cz.netcoop.abilities.IAbility;
import cz.netcoop.devices.IDevice;

public class Message {
    public enum Type {
        REQUEST,
        REPLY
    }

    private Address destination;
    private Address source;
    private Type type;
    private byte abilityID;
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

    public byte getAbilityID() {
        return abilityID;
    }

    public Message(Address source, Type type, byte abilityID, byte[] data) {
        this.destination = AppNetCoop.getApp().getConnector().getBroadcastAddress();
        this.source = source;
        this.type = type;
        this.abilityID = abilityID;
        this.data = data;
    }

    public Message(Address destination, Address source, Type type, byte abilityID, byte[] data) {
        this.destination = destination;
        this.source = source;
        this.type = type;
        this.abilityID = abilityID;
        this.data = data;
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
            result[3] = message.abilityID;

            System.arraycopy(message.data, 0, result, 4, message.data.length);

            return result;
        }

        public static Message parse(byte[] message) {
            AppNetCoop app = AppNetCoop.getApp();

            // dest
            Address destination = new Address(message[0], null);

            // source
            IDevice sourceDev = app.getDevice(message[1]);
            Address source = sourceDev == null ? null : sourceDev.getAddress();

            // type
            Type type = Type.values()[message[2]];

            // ability
            IAbility ability = app.getAbility(message[3]);

            // data
            byte[] data = new byte[message.length - 4];
            System.arraycopy(data, 0, message, 4, message.length);

            return new Message(destination, source, type, ability.getId(), data);
        }
    }
}
