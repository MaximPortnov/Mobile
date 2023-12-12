package com.example.pr18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

public class Activity3 extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";

    ListView lvSimple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        DB3.connectionToDataBase.start();
        try {
            DB3.connectionToDataBase.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        DB3.getNum.start();
        try {
            DB3.getNum.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        int load[] = { 41, 48, 22, 35, 30, 67, 51, 88 };

        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                DB3.nums.size());
        Map<String, Object> m;
        for (int i = 0; i < DB3.nums.size(); i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i+1) + ". Load: " + DB3.nums.get(i) + "%");
            m.put(ATTRIBUTE_NAME_PB, DB3.nums.get(i));
            m.put(ATTRIBUTE_NAME_LL, DB3.nums.get(i));
            data.add(m);
        }
        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB,
                ATTRIBUTE_NAME_LL };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvLoad, R.id.pbLoad, R.id.llLoad };

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item2,
                from, to);
        // Указываем адаптеру свой биндер
        sAdapter.setViewBinder(new MyViewBinder());

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
    }
    class MyViewBinder implements SimpleAdapter.ViewBinder {
        int red = getResources().getColor(R.color.Red);
        int orange = getResources().getColor(R.color.Orange);
        int green = getResources().getColor(R.color.Green);
        @Override
        public boolean setViewValue(View view, Object data,
                                    String textRepresentation) {
            int i = 0;
            if (view.getId() == R.id.llLoad) {
                i = ((Integer) data).intValue();
                if (i < 40) {
                    view.setBackgroundColor(green);
                } else if (i < 70) {
                    view.setBackgroundColor(orange);
                } else {
                    view.setBackgroundColor(red);
                }
                return true;
            } else if (view.getId() == R.id.pbLoad) {
                i = ((Integer) data).intValue();
                ((ProgressBar)view).setProgress(i);
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

