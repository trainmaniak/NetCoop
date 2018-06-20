package cz.netcoop.abilities;

import cz.netcoop.AAppNetCoopObject;

import java.util.Objects;

public abstract class AAbility extends AAppNetCoopObject implements IAbility {
    private byte id;

    @Override
    public final byte getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AAbility aAbility = (AAbility) o;
        return id == aAbility.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
