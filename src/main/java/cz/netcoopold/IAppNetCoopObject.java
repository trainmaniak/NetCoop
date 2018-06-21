package cz.netcoopold;

import cz.netcoopold.servingdaemon.BeaconDaemon;

public interface IAppNetCoopObject {
    AppNetCoop getApp();

    Connector getConnector();
    BeaconDaemon getBeaconDaemon();
}
