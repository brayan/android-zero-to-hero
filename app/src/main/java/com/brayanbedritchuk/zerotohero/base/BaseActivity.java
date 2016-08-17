package com.brayanbedritchuk.zerotohero.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.brayanbedritchuk.zerotohero.R;

public class BaseActivity extends AppCompatActivity {

    protected void addFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.activity_main__fl__fragment, fragment, tag);
        ft.commit();
    }

}
