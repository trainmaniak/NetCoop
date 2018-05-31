package cz.netcoop.ServingDaemon;

import cz.netcoop.AppNetCoop;
import cz.netcoop.Connectors.Connector;

public abstract class AServeDaemon extends Thread implements IServeDaemon {

    private AppNetCoop appNetCoop;

    protected AppNetCoop getAppNetCoop() {
        return appNetCoop;
    }

    private Connector Connector;

    protected Connector getConnector() {
        return Connector;
    }

    protected AServeDaemon(AppNetCoop appNetCoop) {
        this.appNetCoop = appNetCoop;
        this.Connector = appNetCoop.getConnector();
    }

    /**
     * Wait on notification with message from Connector
     * @return message
     * @throws InterruptedException Connector's thread interrupt
     */
    /*
    protected Message waitOnRequest() throws InterruptedException, IOException {
        appNetCoop.Connector.receive();
        while (bufferedMessage == null) {
            wait();
        }

        return bufferedMessage;
    }
    */
}
