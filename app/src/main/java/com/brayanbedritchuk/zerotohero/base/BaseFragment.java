package com.brayanbedritchuk.zerotohero.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    private T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(hasMenu());
        initPresenter();
        getExtrasFromIntent(getActivity().getIntent());
    }

    private void initPresenter() {
        setPresenter(newPresenterInstance());
    }

    protected void getExtrasFromIntent(Intent intent) {
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    protected abstract T newPresenterInstance();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            onActivityResultOk(requestCode, data);

        } else if (resultCode == Activity.RESULT_CANCELED) {
            onActivityResultCanceled(requestCode, data);
        }
    }

    protected void onActivityResultOk(int requestCode, Intent data) {
    }

    protected void onActivityResultCanceled(int requestCode, Intent data) {
    }

    protected boolean hasMenu() {
        return false;
    }

    public T getPresenter() {
        return presenter;
    }

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }
}
