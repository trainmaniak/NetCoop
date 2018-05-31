package cz.netcoop.ServingDaemon;

import cz.netcoop.*;
import cz.netcoop.Connectors.Connector;
import cz.netcoop.Exceptions.ConnectionException;

import java.io.IOException;

public class CServeOther extends AServeDaemon {

    /**
     * In cycle wait on request and answer
     */
    @Override
    public void run() {
        super.run();

        while (true) {
            try {
                Connector conn = getApp().getConnector();

                Message request = conn.receive(conn.getSessionList().get(0).getPortOther());
                Message answer = null; //getApp().getReplier().getAnswer(request);

                conn.send(conn.getSessionList().get(0).getPortOther(), answer);
            } catch (IOException | ConnectionException e) {
                e.printStackTrace();
            }
        }
    }
}
