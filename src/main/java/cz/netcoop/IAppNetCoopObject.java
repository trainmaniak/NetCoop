package cz.netcoop;

import cz.netcoop.AppNetCoop;
import cz.netcoop.Connector;

public interface IAppNetCoopObject {
    AppNetCoop getApp();

    Connector getConnector();

    ConnectorsSecretary getConnectorsSecretary();
}
