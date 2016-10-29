package br.com.sailboat.zerotohero.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<Presenter extends BasePresenter> extends Fragment {

    private Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        setPresenter(newPresenterInstance());
        checkStateAndRestoreViewModel(savedInstanceState);
        extractExtrasFromIntent(getActivity().getIntent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case Activity.RESULT_OK: {
                onActivityResultOk(requestCode, data);
                return;
            }
            case Activity.RESULT_CANCELED: {
                onActivityResultCanceled(requestCode, data);
                return;
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveInstanceState(outState);
    }

    private void checkStateAndRestoreViewModel(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            getPresenter().restoreViewModel(savedInstanceState);
        }
    }

    private void extractExtrasFromIntent(Intent intent) {
        getPresenter().extractExtrasFromIntent(intent);
    }

    @NonNull
    protected abstract Presenter newPresenterInstance();

    protected abstract int getLayoutId();

    protected abstract void initViews(View view);

    protected void onActivityResultOk(int requestCode, Intent data) {
    }

    protected void onActivityResultCanceled(int requestCode, Intent data) {
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
