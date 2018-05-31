package cz.netcoop.ServingDaemon;

import cz.netcoop.AppNetCoop;
import cz.netcoop.DebugPrinter;
import cz.netcoop.MeetingMessage;
import cz.netcoop.MeetingMessageType;

import java.io.IOException;

public class BeaconDaemon extends AServeDaemon {

    @Override
    public void run() {
        super.run();

        publish(MeetingMessage.Builder.createQuery());

        while (true) {
            try {
                MeetingMessage message = getConnector().receiveUDP();
                if (message == null) {
                    DebugPrinter.print("receive done", "message for me");
                    continue;
                } else if (message.getType() == MeetingMessageType.REPLY) { // ask/answer
                    // add to list
                } else if (message.getType() == MeetingMessageType.QUERY){
                    MeetingMessage answer = MeetingMessage.Builder.createReply(
                            getConnector().getMyAddress(),
                            getConnector().getMyNetAddress());
                    
                    publish(answer);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void publish(MeetingMessage message) {
        Thread t = new Thread(() -> {
            try {
                getConnector().sendUDP(message);
            } catch (IOException e) {
                DebugPrinter.print("send UDP", "failed");
                e.printStackTrace();
            }
        });

        t.start();
    }
}
