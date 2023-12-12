package com.example.pr31;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

public class ThirdActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView greetingTextView;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        imageView = findViewById(R.id.imageView);
        gestureDetector = new GestureDetectorCompat(this, new SwipeGestureListener());

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        greetingTextView = findViewById(R.id.greetingTextView);
        Button helloButton = findViewById(R.id.helloButton);

        helloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greetingTextView.setText("Здравствуйте, Ирина Сергеевна!");
            }
        });
    }

    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

            float diffX = event2.getX() - event1.getX();
            float diffY = event2.getY() - event1.getY();

            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                        // Свайп вниз
                    startActivity(new Intent(ThirdActivity.this, MainActivity.class));
                } else {
                        // Свайп вверх
                    startActivity(new Intent(ThirdActivity.this, SecondActivity.class));
                }
                return true;
            }
            return false;
        }
    }
}