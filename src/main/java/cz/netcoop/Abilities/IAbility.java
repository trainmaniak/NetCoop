package cz.netcoop.Abilities;

public interface IAbility {
    public byte getId();

    public String make(String input);

    public String request(String intput);
    public String request();
}
