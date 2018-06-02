package cz.netcoop.servingdaemon;

import cz.netcoop.*;
import cz.netcoop.connectors.Connector;
import cz.netcoop.exceptions.ConnectionException;

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
