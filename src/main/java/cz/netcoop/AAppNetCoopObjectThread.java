package cz.netcoop;

import cz.netcoop.AppNetCoop;
import cz.netcoop.Connector;
import cz.netcoop.IAppNetCoopObject;

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
    public ConnectorsSecretary getConnectorsSecretary() {
        return getApp().getConnectorsSecretary();
    }
}
