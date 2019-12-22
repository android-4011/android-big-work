package com.example.androidbigwork;


import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.haibin.calendarview.CalendarView;
import com.haibin.calendarview.MonthView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    Button button1,button2,button3;
    ArrayList<View> list;
    MyPagerAdapter adapter;
    TextView textView1,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);

        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button1.setOnClickListener((View.OnClickListener) new button1_Click());//月视图
        button2.setOnClickListener((View.OnClickListener) new button2_Click());//周视图
        button3.setOnClickListener((View.OnClickListener) new button3_Click());//日视图

        viewPager=findViewById(R.id.vp_main);
        list=new ArrayList<View>();
        LayoutInflater li=getLayoutInflater();
        list.add(li.inflate(R.layout.monthly_view,null,false));
        list.add(li.inflate(R.layout.weekly_view,null,false));
        list.add(li.inflate(R.layout.daily_view,null,false));
        adapter=new MyPagerAdapter(list);
        viewPager.setAdapter(adapter);
    }

    private class button1_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            button1.setTextColor(Color.parseColor("#00BCD4"));
            button2.setTextColor(Color.parseColor("#88000000"));
            button3.setTextColor(Color.parseColor("#88000000"));
            viewPager.setCurrentItem(0,false);
            Toast.makeText(MainActivity.this,"月视图",Toast.LENGTH_SHORT).show();
        }
    }

    private class button2_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            button1.setTextColor(Color.parseColor("#88000000"));
            button2.setTextColor(Color.parseColor("#00BCD4"));
            button3.setTextColor(Color.parseColor("#88000000"));
            viewPager.setCurrentItem(1,false);
            Toast.makeText(MainActivity.this,"周视图",Toast.LENGTH_SHORT).show();
        }
    }

    private class button3_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            button1.setTextColor(Color.parseColor("#88000000"));
            button2.setTextColor(Color.parseColor("#88000000"));
            button3.setTextColor(Color.parseColor("#00BCD4"));
            viewPager.setCurrentItem(2,false);
            Toast.makeText(MainActivity.this,"日视图",Toast.LENGTH_SHORT).show();
        }
    }
}

