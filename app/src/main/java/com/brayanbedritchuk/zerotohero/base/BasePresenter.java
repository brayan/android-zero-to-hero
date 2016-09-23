package com.brayanbedritchuk.zerotohero.base;

public abstract class BasePresenter {

    private boolean firstSession = true;

    public void onResume() {
        if (isFirstSession()) {
            onResumeFirstSession();
        } else {
            onResumeAfterRestart();
        }

        setFirstSession(false);

        postResume();
    }

    protected void onResumeFirstSession() {
    }

    protected void onResumeAfterRestart() {
    }

    protected void postResume() {
    }

    public boolean isFirstSession() {
        return firstSession;
    }

    public void setFirstSession(boolean firstSession) {
        this.firstSession = firstSession;
    }
}
