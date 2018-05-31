package cz.netcoop;

import cz.netcoop.Abilities.IAbility;

import java.util.List;

public class Message {
    public IDevice destination = null;
    public IDevice source = null;
    public IAbility ability = null;
    public String data;

    public String createMessage() {
        return String.valueOf(destination.getAddress())
                + String.valueOf(source.getAddress())
                + String.valueOf(ability.getId())
                + data;
    }

    public static Message parseMessage(List<IDevice> devices, List<IAbility> abilities, String message) {
        Message newMessage = new Message();

        int destinationNumber = Integer.parseInt(message.substring(0, 3));
        int sourceNumber = Integer.parseInt(message.substring(4, 7));
        int abilityId = Integer.parseInt(message.substring(8, 11));
        newMessage.data = message.substring(12, message.length() - 1);

        for (IDevice dev : devices) {
            if (newMessage.destination == null && dev.getAddress() == destinationNumber) {
                newMessage.destination = dev;
            }

            if (newMessage.source == null && dev.getAddress() == sourceNumber) {
                newMessage.source = dev;
            }
        }

        for (IAbility ab : abilities) {
            if (ab.getId() == abilityId) {
                newMessage.ability = ab;
            }
        }

        return newMessage;
    }
}
