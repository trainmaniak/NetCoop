package cz.netcoop;

import cz.netcoop.abilities.IAbility;

public class ActionHandler {
    private IAbility ability;

    private boolean requestDone = false;

    public ActionHandler(IAbility ability) {
        this.ability = ability;
    }

    public byte[] getRequest() {
        requestDone = true;

        return ability.request();
    }
}
