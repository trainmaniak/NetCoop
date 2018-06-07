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

        // init broadcast
        publish(new MeetingMessage(MeetingMessage.Type.QUERY));

        while (true) {
            try {
                MeetingMessage message = getConnector().receiveUDP();

                if (message != null) {
                    switch (message.getType()) {
                        case QUERY:
                            addDevice(message.getAddress());

                            publish(new MeetingMessage(MeetingMessage.Type.REPLY));
                            break;
                        case REPLY:
                            addDevice(message.getAddress());
                            break;
                        case TCPCONNREQUEST:
                            runListener();

                            publish(new MeetingMessage(MeetingMessage.Type.TCPCONNCONFIRM));
                            break;
                        case TCPCONNCONFIRM:

                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO udelat observer

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

    private void addDevice(Address address) {

        // TODO vlakno protoze add dev je narocnejsi a zpomalovalo by to run (receive)
        Thread t = new Thread(() -> {
            getApp().addDevice(new CommonDevice(address));
        });

        t.start();

        DebugPrinter.print("Thread test", "creatingConnection done");
    }

    private void runListener() {
        Thread t = new Thread(() -> {
            getConnector().listen();
        });

        t.start();
    }
}
