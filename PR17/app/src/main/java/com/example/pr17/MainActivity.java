package com.example.pr17;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements OnClickListener {

    final String LOG_TAG = "myLogs";

    String animal[] = { "Лев", "Слон", "Крокодил", "Тигр", "Пантера",
            "Гепард", "Змея", "Обезьяна", "Кенгуру", "Коала" };
    int population[] = { 200, 500, 100, 150, 80, 70, 50, 120, 90, 60 };
    String habitat[] = { "Африка", "Африка", "Африка", "Азия", "Америка",
            "Африка", "Австралия", "Азия", "Австралия", "Австралия" };

    Button btnAll, btnFunc, btnPopulation, btnSort, btnGroup, btnHaving;
    EditText etFunc, etPopulation, etHabitatPopulation;
    RadioGroup rgSort;

    DBHelper dbHelper;
    SQLiteDatabase db;

    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = (Button) findViewById(R.id.btnAll);
        btnAll.setOnClickListener(this);

        btnFunc = (Button) findViewById(R.id.btnFunc);
        btnFunc.setOnClickListener(this);

        btnPopulation = (Button) findViewById(R.id.btnPopulation);
        btnPopulation.setOnClickListener(this);

        btnSort = (Button) findViewById(R.id.btnSort);
        btnSort.setOnClickListener(this);

        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(this);

        btnHaving = (Button) findViewById(R.id.btnHaving);
        btnHaving.setOnClickListener(this);

        etFunc = (EditText) findViewById(R.id.etFunc);
        etPopulation = (EditText) findViewById(R.id.etPopulation);
        etHabitatPopulation = (EditText) findViewById(R.id.etHabitatPopulation);

        rgSort = (RadioGroup) findViewById(R.id.rgSort);

        dbHelper = new DBHelper(this);
        // подключаемся к базе
        db = dbHelper.getWritableDatabase();

        // проверка существования записей
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();
            // заполним таблицу
            for (int i = 0; i < 10; i++) {
                cv.put("animal", animal[i]);
                cv.put("population", population[i]);
                cv.put("habitat", habitat[i]);
                Log.d(LOG_TAG, "id = " + db.insert("mytable", null, cv));
            }
        }
        c.close();
        dbHelper.close();
        // эмулируем нажатие кнопки btnAll
        onClick(btnAll);

    }

    @SuppressLint("Range")
    public void onClick(View v) {

        // подключаемся к базе
        db = dbHelper.getWritableDatabase();

        // данные с экрана
        String sFunc = etFunc.getText().toString();
        String sPopulation = etPopulation.getText().toString();
        String sHabitatPopulation = etHabitatPopulation.getText().toString();

        // переменные для query
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        // курсор
        Cursor c = null;
        try {

            // определяем нажатую кнопку
            if (v.getId() == R.id.btnAll) {
                Log.d(LOG_TAG, "--- Все записи ---");
                c = db.query("mytable", null, null, null, null, null, null);
            } else if (v.getId() == R.id.btnFunc) {
                Log.d(LOG_TAG, "--- Функция " + sFunc + " ---");
                columns = new String[] { sFunc };
                c = db.query("mytable", columns, null, null, null, null, null);
            } else if (v.getId() == R.id.btnPopulation) {
                Log.d(LOG_TAG, "--- Численность больше " + sPopulation + " ---");
                selection = "population > ?";
                selectionArgs = new String[] { sPopulation };
                c = db.query("mytable", null, selection, selectionArgs, null, null, null);
            } else if (v.getId() == R.id.btnGroup) {
                Log.d(LOG_TAG, "--- Численность по месту обитания ---");
                columns = new String[] { "habitat", "sum(population) as population" };
                groupBy = "habitat";
                c = db.query("mytable", columns, null, null, groupBy, null, null);
            } else if (v.getId() == R.id.btnHaving) {
                Log.d(LOG_TAG, "--- Места обитания с численностью больше " + sHabitatPopulation + " ---");
                columns = new String[] { "habitat", "sum(population) as population" };
                groupBy = "habitat";
                having = "sum(population) > " + sHabitatPopulation;
                c = db.query("mytable", columns, null, null, groupBy, having, null);
            } else if (v.getId() == R.id.btnSort) {
                // сортировка по
                if (rgSort.getCheckedRadioButtonId() == R.id.rName) {
                    Log.d(LOG_TAG, "--- Сортировка по названию ---");
                    orderBy = "animal";
                } else if (rgSort.getCheckedRadioButtonId() == R.id.rPopulation) {
                    Log.d(LOG_TAG, "--- Сортировка по численности ---");
                    orderBy = "population";
                } else if (rgSort.getCheckedRadioButtonId() == R.id.rHabitat) {
                    Log.d(LOG_TAG, "--- Сортировка по месту обитания ---");
                    orderBy = "habitat";
                }
                c = db.query("mytable", null, null, null, null, null, orderBy);
            }
        }
        catch (Exception e){
            Log.e("f", "onClick: " + e.toString());
        }

        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);

                } while (c.moveToNext());
            }
            c.close();
        } else
            Log.d(LOG_TAG, "Cursor is null");

        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement," + "animal text,"
                    + "population integer," + "habitat text" + ");");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}