package cz.netcoop.abilities;

public class Ping extends AAbility {
    private byte id = 0x1;

    @Override
    public byte[] generate() {
        return new byte[]{0x0};
    }

    @Override
    public byte[] make(byte[] input) {
        if (input[0] == 0x0) {
            return new byte[]{0x1};
        }

        return null;
    }

    @Override
    public String request(String intput) {
        return null;
    }

    @Override
    public String request() {
        return null;
    }
}
