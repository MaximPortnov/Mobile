package com.example.pr18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.os.Bundle;
import android.view.MenuItem;

public class Activity4 extends AppCompatActivity {

    private static final int CM_DELETE_ID = 1;

    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView lvSimple;
    SimpleAdapter sAdapter;
    ArrayList<Map<String, Object>> data;
    Map<String, Object> m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        data = new ArrayList<Map<String, Object>>();
        for (int i = 1; i < 5; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "sometext " + i);
            m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher_background);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvText, R.id.ivImg };

        // создаем адаптер
        sAdapter = new SimpleAdapter(this, data, R.layout.item3, from, to);

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
        registerForContextMenu(lvSimple);
    }
    public void onButtonClick(View v) {
        // создаем новый Map
        m = new HashMap<String, Object>();
        m.put(ATTRIBUTE_NAME_TEXT, "sometext " + (data.size() + 1));
        m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher_background);
        // добавляем его в коллекцию
        data.add(m);
        // уведомляем, что данные изменились
        sAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            // получаем инфу о пункте списка
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
            // удаляем Map из коллекции, используя позицию пункта в списке
            data.remove(acmi.position);
            // уведомляем, что данные изменились
            sAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
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

