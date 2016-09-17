package com.brayanbedritchuk.zerotohero.base;

import android.os.AsyncTask;

public abstract class BaseAsyncTask extends AsyncTask<Void, Void, Exception> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Exception doInBackground(Void... params) {
        try {
            onRunningInBackground();
            return null;
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Exception e) {
        super.onPostExecute(e);
        if (e == null) {
            onSuccess();
        } else {
            onFail(e);
        }
    }

    protected abstract void onRunningInBackground() throws Exception;
    protected abstract void onSuccess();
    protected abstract void onFail(Exception e);

}
