package cz.netcoopold.servingdaemon;

import cz.netcoopold.Address;
import cz.netcoopold.DebugPrinter;
import cz.netcoopold.Message;
import cz.netcoopold.abilities.StartDating;
import cz.netcoopold.devices.CommonDevice;

import java.io.IOException;

public class BeaconDaemon extends AServeDaemon {

    @Override
    public void run() {
        super.run();

        // init broadcast
        publish(new Message(Message.Type.REQUEST, new StartDating()));

        while (true) {
            try {
                Message message = getConnector().receive();

                if (message != null) {
                    switch (message.getType()) {
                        case REQUEST:
                            addDevice(message.getDestination());

                            publish(new Message(Message.Type.REPLY, new StartDating()));
                            break;
                        case REPLY:
                            addDevice(message.getDestination());
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO udelat observer

    public void publish(Message message) {
        Thread t = new Thread(() -> { // TODO proc nove vlakno??
            try {
                getConnector().send(message);
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
}
