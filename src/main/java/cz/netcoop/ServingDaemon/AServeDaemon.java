package cz.netcoop.ServingDaemon;

import cz.netcoop.AAppNetCoop;
import cz.netcoop.Connector;

public abstract class AServeDaemon extends Thread implements IServeDaemon {

    private AAppNetCoop appNetCoop;

    protected AAppNetCoop getAppNetCoop() {
        return appNetCoop;
    }

    private Connector connector;

    protected Connector getConnector() {
        return connector;
    }

    protected AServeDaemon(AAppNetCoop appNetCoop) {
        this.appNetCoop = appNetCoop;
        this.connector = appNetCoop.getConnector();
    }

    /**
     * Wait on notification with message from Connector
     * @return message
     * @throws InterruptedException Connector's thread interrupt
     */
    /*
    protected Message waitOnRequest() throws InterruptedException, IOException {
        appNetCoop.connector.receive();
        while (bufferedMessage == null) {
            wait();
        }

        return bufferedMessage;
    }
    */
}
