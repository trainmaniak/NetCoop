package cz.netcoop.servingdaemon;

import cz.netcoop.*;
import cz.netcoop.connectors.Connector;
import cz.netcoop.exceptions.ConnectionException;

import java.io.IOException;

public class SServeOther extends AServeDaemon {

    private void foo() {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Connector conn = getConnector();

                Message message = conn.receive(conn.getSessionList().get(0).getPortOther());

                //IDevice destination = ((AppNetCoopServer)getAppNetCoop()).getRouter().getDestinationDevice(message);
                //conn.send(conn.getSession(destination).getPortOther(), message);
            } catch (IOException | ConnectionException e) {
                e.printStackTrace();
            }
        }
    }
}
