package cz.netcoop.ServingDaemon;

import cz.netcoop.AppNetCoop;
import cz.netcoop.DebugPrinter;

import java.io.IOException;

public class Beacon extends AServeDaemon {

    public Beacon(AppNetCoop appNetCoop) {
        super(appNetCoop);
    }

    @Override
    public void run() {
        super.run();

        publish("ask");

        while (true) {
            try {
                String message = getConnector().receiveUDP();
                if (message == "answer") { // ask/answer
                    // add to list
                } else {
                    publish("reply");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void publish(String data) {
        Thread t = new Thread(() -> {
            try {
                getConnector().sendUDP(data);
            } catch (IOException e) {
                DebugPrinter.print("send UDP", "failed");
                e.printStackTrace();
            }
        });

        t.start();
    }
}
