package cz.netcoopold;

import cz.netcoopold.abilities.IAbility;

import java.time.LocalDateTime;

public class ActionHandler {
    private IAbility ability;
    private byte[] data = null;

    private boolean requestDone = false;
    private LocalDateTime timeStamp = null;

    public ActionHandler(IAbility ability, byte[] data) {
        this.ability = ability;
        this.data = data;
    }

    public byte[] getRequest() {
        requestDone = true;

        timeStamp = LocalDateTime.now();

        return ability.request();
    }
}
