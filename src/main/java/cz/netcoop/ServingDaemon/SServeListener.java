package cz.netcoop.ServingDaemon;

public class SServeListener extends AServeDaemon {

    @Override
    public void run() {
        while (true) {
            getConnector().listen();
        }
    }
}
