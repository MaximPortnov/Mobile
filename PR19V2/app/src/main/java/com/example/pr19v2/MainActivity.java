package com.example.pr19v2;

//import  androidx.appcompat.app.AppCompatActivity;
//import android.app.DatePickerDialog;
//import android.app.TimePickerDialog;
//import android.os.Bundle;
//import android.text.format.DateUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.TextView;
//import android.widget.TimePicker;
//import java.util.Calendar;
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView dateTimeDisplay;
    private Button dateButton;
    private Button timeButton;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateTimeDisplay = (TextView) findViewById(R.id.date_time_display);
        dateButton = (Button) findViewById(R.id.date_button);
        timeButton = (Button) findViewById(R.id.time_button);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        updateDateTimeDisplay();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        Intent intent;
//        if (v.getId() == R.id.second_activity_button) {
//            intent = new Intent(MainActivity.this, SecondActivity.class);
//            startActivity(intent);
//        }
//    }

    private void updateDateTimeDisplay() {
        dateTimeDisplay.setText(dateFormat.format(calendar.getTime()) + " " + timeFormat.format(calendar.getTime()));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateTimeDisplay();
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            updateDateTimeDisplay();
        }
    };
}