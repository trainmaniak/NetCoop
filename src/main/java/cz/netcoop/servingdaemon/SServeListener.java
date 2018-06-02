package cz.netcoop.servingdaemon;

public class SServeListener extends AServeDaemon {

    @Override
    public void run() {
        while (true) {
            getConnector().listen();
        }
    }
}
