package br.com.sailboat.zerotohero.base;

import android.os.AsyncTask;

public abstract class BaseAsyncTask extends AsyncTask<Void, Void, Exception> {

    @Override
    protected Exception doInBackground(Void... params) {
        try {
            onDoInBackground();
            return null;
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Exception e) {
        if (e == null) {
            onSuccess();
        } else {
            onFail(e);
        }
    }

    protected abstract void onDoInBackground() throws Exception;

    protected abstract void onSuccess();

    protected abstract void onFail(Exception e);

}
