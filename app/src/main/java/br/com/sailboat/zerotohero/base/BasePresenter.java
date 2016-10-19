package br.com.sailboat.zerotohero.base;

import android.content.Intent;
import android.os.Bundle;

public abstract class BasePresenter {

    private boolean firstSession = true;

    public void onResume() {
        checkFirstSessionAndResume();
        postResume();
    }

    private void checkFirstSessionAndResume() {
        if (isFirstSession()) {
            onResumeFirstSession();
            setFirstSession(false);
        } else {
            onResumeAfterRestart();
        }
    }

    protected void onResumeFirstSession() {
    }

    protected void onResumeAfterRestart() {
    }

    protected void postResume() {
    }

    protected void onSaveInstanceState(Bundle outState) {
    }

    protected void restoreViewModel(Bundle savedInstanceState) {
    }

    public void extractExtrasFromIntent(Intent intent) {
    }

    public boolean isFirstSession() {
        return firstSession;
    }

    public void setFirstSession(boolean firstSession) {
        this.firstSession = firstSession;
    }

}