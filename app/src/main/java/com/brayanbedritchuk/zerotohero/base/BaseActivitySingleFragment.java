package com.brayanbedritchuk.zerotohero.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.brayanbedritchuk.zerotohero.R;

public abstract class BaseActivitySingleFragment<T extends Fragment> extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        if (savedInstanceState == null) {
            addFragment(R.id.frame_layout, newFragmentInstance());
        }
    }

    protected abstract T newFragmentInstance();

    private void addFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(id, fragment).commit();
    }

}
