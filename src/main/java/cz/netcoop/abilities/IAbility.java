package cz.netcoop.abilities;

public interface IAbility {
    byte getId();

    byte[] generateRequest();
    byte[] make(byte[] input);
    byte[] interpretReply(byte[] input);
}
