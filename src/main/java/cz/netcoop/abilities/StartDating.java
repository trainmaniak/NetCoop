package cz.netcoop.abilities;

import cz.netcoop.AppNetCoop;

public class StartDating extends AAbility {
    private byte id = 0x0;

    @Override
    public byte[] request() {
        return new byte[0];
    }

    @Override
    public byte[] make(byte[] input) {
        return new byte[0];
    }
}
