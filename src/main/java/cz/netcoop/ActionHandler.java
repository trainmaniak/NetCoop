package cz.netcoop;

import cz.netcoop.abilities.IAbility;

import java.time.LocalDateTime;

public class ActionHandler {
    private IAbility ability;

    private boolean requestDone = false;
    private LocalDateTime timeStamp = null;
    private byte[] response = null;

    public ActionHandler(IAbility ability) {
        this.ability = ability;
    }

    public void setResponse(byte[] response) {
        this.response = response;
    }

    public byte[] getRequest() {
        requestDone = true;

        timeStamp = LocalDateTime.now();

        return ability.request();
    }
}
