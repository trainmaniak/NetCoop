package cz.netcoop;

import cz.netcoop.Connectors.Connector;
import cz.netcoop.ServingDaemon.BeaconDaemon;

public abstract class AAppNetCoopObject implements IAppNetCoopObject {
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
