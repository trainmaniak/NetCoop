package cz.netcoopold;

import cz.netcoopold.abilities.IAbility;

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
            jh
            result[2] = (byte)message.type.ordinal();
            result[3] = message.abilityID;

            System.arraycopy(message.data, 0, result, 4, message.data.length);

            return result;
        }

        public static Message parse(byte[] message) {
            AppNetCoop app = AppNetCoop.getApp();

            byte destAddr = message[0];
            Address destination = app.getDevice(destAddr).getAddress();
            Address source = app.getDevice(message[1]).getAddress();
            Type type = Type.values()[message[2]];

            byte[] data = new byte[message.length - 4];
            System.arraycopy(data, 0, message, 4, message.length);

            IAbility ability = app.getAbility(message[3]);
            app.getClientHandler(destAddr).addAction(ability.getId(), data);

            return new Message(destination, source, type, ability.getId(), data);
        }
    }
}
