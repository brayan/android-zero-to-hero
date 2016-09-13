package com.brayanbedritchuk.zerotohero.base;

public abstract class BaseViewModel {

    private boolean firstSession = true;

    public boolean isFirstSession() {
        return firstSession;
    }

    public void setFirstSession(boolean firstSession) {
        this.firstSession = firstSession;
    }

}
