package cz.netcoop.ServingDaemon;

import cz.netcoop.AAppNetCoopObjectThread;
import cz.netcoop.AppNetCoop;
import cz.netcoop.Connectors.Connector;
import cz.netcoop.IAppNetCoopObject;

public abstract class AServeDaemon extends AAppNetCoopObjectThread implements IServeDaemon {

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
