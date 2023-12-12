package com.example.pr28;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView sunImageView = findViewById(R.id.imageView3);
        @SuppressLint("ResourceType") Animation sunRiseAnimation = AnimationUtils.loadAnimation(this, R.drawable.sun_rise);
        sunImageView.startAnimation(sunRiseAnimation);



        ImageView train = findViewById(R.id.train);
        ObjectAnimator animator = ObjectAnimator.ofFloat(train, "translationX", 2000f, -500f);
        animator.setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();

    }
}