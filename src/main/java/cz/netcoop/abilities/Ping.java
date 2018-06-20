package cz.netcoop.abilities;

public class Ping extends AAbility {
    private byte id = 0x1;

    @Override
    public byte[] generateRequest() {
        return "answer me".getBytes();
    }

    @Override
    public byte[] make(byte[] input) {
        if (input[0] == 0x0) {
            return new byte[]{0x1};
        }

        return null;
    }
}
