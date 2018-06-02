package cz.netcoop;

import cz.netcoop.connectors.Connector;
import cz.netcoop.servingdaemon.BeaconDaemon;

public interface IAppNetCoopObject {
    AppNetCoop getApp();

    Connector getConnector();
    BeaconDaemon getBeaconDaemon();
}
