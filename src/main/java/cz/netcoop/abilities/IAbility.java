package cz.netcoop.abilities;

public interface IAbility {
    byte getId();

    byte[] request();
    byte[] make(byte[] input);

}
