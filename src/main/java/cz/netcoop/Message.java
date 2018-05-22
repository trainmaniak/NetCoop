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

    public Message parseMessage(List<IDevice> devices, List<IAbility> abilities, String message) {

        int destinationNumber = Integer.parseInt(message.substring(0, 3));
        int sourceNumber = Integer.parseInt(message.substring(4, 7));
        int abilityId = Integer.parseInt(message.substring(8, 11));
        data = message.substring(12, message.length() - 1);

        for (IDevice dev : devices) {
            if (destination == null && dev.getAddress() == destinationNumber) {
                destination = dev;
            }

            if (source == null && dev.getAddress() == sourceNumber) {
                source = dev;
            }
        }

        for (IAbility ab : abilities) {
            if (ab.getId() == abilityId) {
                ability = ab;
            }
        }

        return this;
    }
}
