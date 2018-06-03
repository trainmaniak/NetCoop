package cz.netcoop.servingdaemon;

import cz.netcoop.Address;
import cz.netcoop.DebugPrinter;
import cz.netcoop.MeetingMessage;
import cz.netcoop.devices.CommonDevice;

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
                } else {
                    createConnection(message.getAddress());

                    if (message.getType() == MeetingMessage.Type.QUERY){
                        MeetingMessage answer = new MeetingMessage(MeetingMessage.Type.REPLY);
                        publish(answer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void publish(MeetingMessage message) {
        Thread t = new Thread(() -> { // TODO proc nove vlakno??
            try {
                getConnector().sendUDP(message);
            } catch (IOException e) {
                DebugPrinter.print("send UDP", "failed");
                e.printStackTrace();
            }
        });

        t.start();
    }

    public void createConnection(Address address) {

        // TODO vlakno nefunguje ??? nevytvori se nove

        // TODO vlakno protoze add dev je narocnejsi a zpomalovalo by to run (receive)
        Thread t = new Thread(() -> {
            getApp().addDevice(new CommonDevice(address));
        });
        t.start();

        DebugPrinter.print("Thread test", "creatingConnection done");
    }
}
