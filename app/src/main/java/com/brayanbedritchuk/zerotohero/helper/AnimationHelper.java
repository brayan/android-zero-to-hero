package com.brayanbedritchuk.zerotohero.helper;

import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;

public class AnimationHelper {

    public static void performScaleUpAnimation(View view) {
        view.animate().scaleX(1)
                .scaleY(1)
                .setInterpolator(new FastOutSlowInInterpolator());
    }

    public static void performScaleDownAnimation(View view) {
        view.animate().scaleX(0)
                .scaleY(0)
                .setInterpolator(new FastOutSlowInInterpolator());
    }

}
