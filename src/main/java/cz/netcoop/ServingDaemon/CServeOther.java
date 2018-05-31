package cz.netcoop.ServingDaemon;

import cz.netcoop.*;
import cz.netcoop.Connectors.Connector;
import cz.netcoop.Exceptions.ConnectionException;

import java.io.IOException;

public class CServeOther extends AServeDaemon {
    public CServeOther(AppNetCoopClient appNetCoopClient) {
        super(appNetCoopClient);
    }

    /**
     * In cycle wait on request and answer
     */
    @Override
    public void run() {
        super.run();

        while (true) {
            try {
                Connector conn = getAppNetCoop().getConnector();

                Message request = conn.receive(conn.getSessionList().get(0).getPortOther());
                Message answer = ((AppNetCoopClient)getAppNetCoop())
                        .getReplier().getAnswer(request);

                conn.send(conn.getSessionList().get(0).getPortOther(), answer);
            } catch (IOException | ConnectionException e) {
                e.printStackTrace();
            }
        }
    }
}
