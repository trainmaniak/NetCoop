package cz.netcoop;

import cz.netcoop.Connectors.Connector;
import cz.netcoop.ServingDaemon.BeaconDaemon;

public interface IAppNetCoopObject {
    AppNetCoop getApp();

    Connector getConnector();
    BeaconDaemon getBeaconDaemon();
}
