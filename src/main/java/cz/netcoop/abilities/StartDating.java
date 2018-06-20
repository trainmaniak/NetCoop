package cz.netcoop.abilities;

import cz.netcoop.Address;
import cz.netcoop.DebugPrinter;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class StartDating extends AAbility {
    private byte id = 0x0;

    @Override
    public byte[] generateRequest() {
        return getConnector().getMyAddress().getIpAddressByte();
    }

    @Override
    public byte[] make(byte[] input) {

        // TODO is makeable method
        if (input.length != 4) {
            DebugPrinter.print("error", "execute ability");
            return null;
        }

        try {
            getConnectorsSecretary().getMessage().getDestination().addIpAddress(InetAddress.getByAddress(input));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // poslat ip
        return new byte[] { 0x0 };
    }

    @Override
    public byte[] interpretReply(byte[] input) {
        Address otherAddress = getConnectorsSecretary().getMessage().getSource();
        getApp().getAddDevice(otherAddress);

        return input;
    }
}
