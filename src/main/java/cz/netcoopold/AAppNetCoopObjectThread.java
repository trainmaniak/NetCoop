package cz.netcoopold;

import cz.netcoopold.servingdaemon.BeaconDaemon;

public abstract class AAppNetCoopObjectThread extends Thread implements IAppNetCoopObject {
    @Override
    public final AppNetCoop getApp() {
        return AppNetCoop.getApp();
    }

    @Override
    public final Connector getConnector() {
        return getApp().getConnector();
    }

    @Override
    public final BeaconDaemon getBeaconDaemon() {
        return getApp().getBeaconDaemon();
    }
}
