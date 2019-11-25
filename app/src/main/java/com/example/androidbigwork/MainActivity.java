package com.example.androidbigwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;





/**
 * 首先继承月视图，假如我们想实现高仿魅族的日历
 */


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
