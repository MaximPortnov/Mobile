package com.example.pr18;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3, button4, button5, button6, button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.button1) {
            intent = new Intent(MainActivity.this, Activity1.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button2) {
            intent = new Intent(MainActivity.this, Activity2.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button3) {
            intent = new Intent(MainActivity.this, Activity3.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button4) {
            intent = new Intent(MainActivity.this, Activity4.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button5) {
            intent = new Intent(MainActivity.this, Activity5.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button6) {
            intent = new Intent(MainActivity.this, Activity6.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button7) {
            intent = new Intent(MainActivity.this, Activity7.class);
            startActivity(intent);
        }
    }
}
