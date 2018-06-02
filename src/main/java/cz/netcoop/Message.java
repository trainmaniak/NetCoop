package cz.netcoop;

import cz.netcoop.abilities.IAbility;
import cz.netcoop.devices.IDevice;

public class Message {
    private IDevice destination = null;
    private IDevice source = null;
    private IAbility ability = null;
    private String data;

    public Message(IDevice destination, IDevice source, IAbility ability, String data) {
        this.destination = destination;
        this.source = source;
        this.ability = ability;
        this.data = data;
    }

    public String assemble() {
        return String.valueOf(destination.getAddress())
                + String.valueOf(source.getAddress())
                + String.valueOf(ability.getId())
                + data;
    }

    public static Message parseMessage(String message) {
        AppNetCoop app = AppNetCoop.getApp();

        int destinationNumber = Integer.parseInt(message.substring(0, 4));
        int sourceNumber      = Integer.parseInt(message.substring(4, 8));
        int abilityId         = Integer.parseInt(message.substring(8, 12));
        String data           = message.substring(12, message.length());

        IDevice destination = null;
        IDevice source = null;
        for (IDevice dev : app.getDeviceList()) {
            if (destination == null && dev.getAddress() == destinationNumber) {
                destination = dev;
            }

            if (source == null && dev.getAddress() == sourceNumber) {
                source = dev;
            }
        }

        IAbility ability = null;
        for (IAbility ab : app.getAbilityList()) {
            if (ab.getId() == abilityId) {
                ability = ab;
                break;
            }
        }

        return new Message(destination, source, ability, data);
    }
}
