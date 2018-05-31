/*
package cz.netcoop;

import cz.netcoop.Connectors.Connector;
import cz.netcoop.ServingDaemon.*;

public class AppNetCoopClient extends AppNetCoop {
    private CServeOther serveOther;
    private CServeMe serveMe;

    private Replier replier;

    public Replier getReplier() {
        return replier;
    }

    private Thread tServeOther;
    //private Thread tServeMe;

    public AppNetCoopClient(String serverIP) {
        super();

        serveOther = new CServeOther(this);
        serveMe = new CServeMe(this);

        tServeOther = new Thread(serveOther);
        //tServeMe = new Thread(serveMe);

        replier = new Replier();

        getConnector().connect(serverIP);
    }

    public void start() {
        // start new thread
        tServeOther.run();
        // call run in this thread
        serveMe.run();
    }
}
*/