package cz.netcoop.servingdaemon;

import cz.netcoop.AAppNetCoopObjectThread;

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
