package cz.netcoop.ServingDaemon;

import cz.netcoop.*;
import cz.netcoop.Exceptions.ConnectionException;

import java.io.IOException;
import java.util.Scanner;

public class CServeMe extends AServeDaemon {
    Scanner scan = new Scanner(System.in);

    public CServeMe(AppNetCoopClient appNetCoopClient) {
        super(appNetCoopClient);
    }

    @Override
    public void run() {
        super.run();

        while (true) {
            try {
                String text = scan.nextLine();

                Message message = new Message();
                message.destination = getAppNetCoop().getDeviceList().get(0); // TODO testovaci
                message.ability = getAppNetCoop().getAbilityList().get(0);
                message.data = text;

                Port port = getConnector().getSessionList().get(0).getPortMe();
                getConnector().send(port, message);
            } catch (ConnectionException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
