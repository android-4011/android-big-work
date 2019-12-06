package com.example.androidbigwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.haibin.calendarview.CalendarView;

public class Main2Activity extends AppCompatActivity {

    CalendarView calendarView;
    TextView textView2;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        textView2=findViewById(R.id.textView);
        textView1=findViewById(R.id.textView2);

        calendarView=findViewById(R.id.calendarView1);
        textView2.setText(String.valueOf(calendarView.getCurMonth()));
        textView1.setText(String.valueOf(calendarView.getCurYear()));
        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                textView2.setText(String.valueOf(month));
            }
        });
        calendarView.setOnYearChangeListener(new CalendarView.OnYearChangeListener() {
            @Override
            public void onYearChange(int year) {
                textView1.setText(String.valueOf(year));
            }
        });
    }
}
