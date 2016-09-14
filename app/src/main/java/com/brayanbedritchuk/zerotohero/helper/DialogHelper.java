package com.brayanbedritchuk.zerotohero.helper;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

public class DialogHelper extends DialogFragment {

    private static final String TAG = "TAG_DIALOG_HELPER";
    private static final String ERROR_TITLE = "Oopss";

    private String message;
    private String title;

    public static void showErrorMessage(FragmentManager manager, String message) {
        showMessage(manager, ERROR_TITLE, message);
    }

    public static void showMessage(FragmentManager manager, String title, String msg) {
        DialogHelper dialog = new DialogHelper();
        dialog.setTitle(title);
        dialog.setMessage(msg);

        dialog.show(manager, TAG);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(getTitle());
        dialog.setMessage(getMessage());
        dialog.setPositiveButton(android.R.string.ok, null);

        return dialog.create();
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
