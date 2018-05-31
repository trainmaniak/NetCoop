package cz.netcoop.ServingDaemon;

import cz.netcoop.AppNetCoopServer;

public class SServeListener extends AServeDaemon {
    public SServeListener(AppNetCoopServer appNetCoopServer) {
        super(appNetCoopServer);
    }

    @Override
    public void run() {
        while (true) {
            getConnector().listen();
        }
    }
}
