package cz.netcoopold.servingdaemon;

import cz.netcoopold.AAppNetCoopObjectThread;

public abstract class AServeDaemon extends AAppNetCoopObjectThread implements IServeDaemon {

    /**
     * Wait on notification with message from Connector
     * @return message
     * @throws InterruptedException Connector's thread interrupt
     */
    /*
    protected MessageOld waitOnRequest() throws InterruptedException, IOException {
        appNetCoop.Connector.receive();
        while (bufferedMessage == null) {
            wait();
        }

        return bufferedMessage;
    }
    */
}
