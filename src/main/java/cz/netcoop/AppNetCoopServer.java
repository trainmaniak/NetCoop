package cz.netcoop;

import cz.netcoop.ServingDaemon.*;

public class AppNetCoopServer extends AppNetCoop {
    private SServeOther serveOther;
    private SServeListener serveListener;

    private Router router;

    public Router getRouter() {
        return router;
    }

    private Thread tServeOther;
    private Thread tListener;

    public AppNetCoopServer() {
        super();

        serveOther = new SServeOther(this);
        serveListener = new SServeListener(this);

        router = new Router();

        tServeOther = new Thread(serveOther);
    }

    public void start() {
        // start new thread
        //tServeOther.run(); //tServeMe.run();
        // call run in this thread
        serveListener.run();
    }
}
