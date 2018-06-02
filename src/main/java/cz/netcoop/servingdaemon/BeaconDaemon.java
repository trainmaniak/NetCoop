package cz.netcoop.servingdaemon;

import cz.netcoop.DebugPrinter;
import cz.netcoop.MeetingMessage;

import java.io.IOException;

public class BeaconDaemon extends AServeDaemon {

    @Override
    public void run() {
        super.run();

        publish(new MeetingMessage(MeetingMessage.Type.QUERY));

        while (true) {
            try {
                MeetingMessage message = getConnector().receiveUDP();
                if (message == null) {
                    continue;
                } else if (message.getType() == MeetingMessage.Type.REPLY) { // ask/answer
                    // TODO add to list
                } else if (message.getType() == MeetingMessage.Type.QUERY){
                    MeetingMessage answer = new MeetingMessage(MeetingMessage.Type.REPLY);
                    
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
