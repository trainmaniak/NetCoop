package cz.netcoop;

import cz.netcoop.abilities.IAbility;
import cz.netcoop.devices.IDevice;

public class Message {
    public enum Type {
        QUERY,
        REPLY
    }

    private Type type;
    private IAbility ability;
    private String data;

    public IAbility getAbility() {
        return ability;
    }

    public Message(IAbility ability, String data) {
        this.ability = ability;
        this.data = data;
    }

    public static class Builder {
        public static byte[] build(Message message) {
            byte[] result = new byte[256];

            result[0] = (byte)message.type.ordinal();
            result[1] = message.ability.getId();

            message.ability.request();
        }

        public static Message parse(byte[] message) {

        }
    }
}
