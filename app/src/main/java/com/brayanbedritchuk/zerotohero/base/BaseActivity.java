package com.brayanbedritchuk.zerotohero.base;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected void addFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(id, fragment).commit();
    }

}
