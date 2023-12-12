package com.example.db_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView texts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB.connectionToDataBase.start();
        try {
            DB.connectionToDataBase.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        DB.getNum.start();
        try {
            DB.getNum.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String s = "";
        for (int i : DB.nums) {
            s += " " + String.valueOf(i);
        }
        texts = findViewById(R.id.texts);
        texts.setText(s);

        DB.closeConnectionToDataBase.start();
        try {
            DB.closeConnectionToDataBase.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}