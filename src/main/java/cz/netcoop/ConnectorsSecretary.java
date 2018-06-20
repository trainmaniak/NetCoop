package cz.netcoop;

import cz.netcoop.abilities.IAbility;
import cz.netcoop.abilities.StartDating;

public class ConnectorsSecretary extends AAppNetCoopObjectThread {
    private Message message = null;

    public Message getMessage() {
        return message;
    }

    @Override
    public void run() {
        super.run();

        message = getConnector().pullMessageBuffer();

        if (message == null) {
            return;
        }

        IAbility ability = getApp().getAbility(message.getAbilityID());

        switch (message.getType()) {
            case REQUEST:
                getApp().addDevice(message.getDestination());

                getConnector().send(new Message(message.getSource(), message.getDestination(), Message.Type.REPLY, ability.getId(), ability.make(new byte[])));
                break;
            case REPLY:
                // TODO do presenteru dat
                break;
        }
    }
}
