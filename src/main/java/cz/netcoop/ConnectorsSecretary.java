package cz.netcoop;

import cz.netcoop.abilities.IAbility;

import java.io.IOException;

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

        IAbility ability = getApp().getAbility(message.getAbilityId());

        try {
            switch (message.getType()) {
                case REQUEST:
                    Message reply = new Message(
                            message.getSourceId(),
                            message.getDestinationId(),
                            Message.Type.REPLY,
                            message.getAbilityId(),
                            ability.makeRequest(message.getData()));


                    getConnector().send(reply);
                    break;
                case REPLY:
                    // TODO do presenteru dat
                    break;
            }
        } catch(IOException e){
            e.printStackTrace();

            DebugPrinter.print("warning", "ConnectorsSecretary - execute ability");
        }
    }
}
