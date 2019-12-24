package com.example.androidbigwork;


import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.haibin.calendarview.MonthView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    Button button1,button2,button3;
    ArrayList<View> list;
    MyPagerAdapter adapter;
    TextView textView1,textView2;
    CalendarView calendarView;
    CalendarView calendarView1;
    CalendarView calendarView2;
    FloatingActionButton floatbutton1;
    FloatingActionButton floatbutton2;
    Calendar calendar1;
    EditText editText1;
    EditText editText2;
    ConstraintLayout add_agenda;
    TextView textView_agenda;
    SharedPreferences settings;

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
        floatbutton1=findViewById(R.id.floatingActionButton1);
        floatbutton2=findViewById(R.id.floatingActionButton2);

        textView_agenda=findViewById(R.id.textView_agenda);
        textView_agenda.setText("测试textview是否有问题");
        viewPager=findViewById(R.id.vp_main);
        list=new ArrayList<View>();
        LayoutInflater li=getLayoutInflater();
        list.add(li.inflate(R.layout.monthly_view,null,false));
        list.add(li.inflate(R.layout.weekly_view,null,false));
        list.add(li.inflate(R.layout.daily_view,null,false));
        adapter=new MyPagerAdapter(list);
        viewPager.setAdapter(adapter);

        calendarView=list.get(0).findViewById(R.id.calendarView);
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
        calendarView1=list.get(1).findViewById(R.id.calendarView1);
        calendarView2=list.get(2).findViewById(R.id.calendarView2);
        calendarView1.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                textView2.setText(String.valueOf(month));
            }
        });
        calendarView1.setOnYearChangeListener(new CalendarView.OnYearChangeListener() {
            @Override
            public void onYearChange(int year) {
                textView1.setText(String.valueOf(year));
            }
        });
        calendarView2.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                textView2.setText(String.valueOf(month));
            }
        });
        calendarView2.setOnYearChangeListener(new CalendarView.OnYearChangeListener() {
            @Override
            public void onYearChange(int year) {
                textView1.setText(String.valueOf(year));
            }
        });
        floatbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.scrollToCurrent();
                calendarView1.scrollToCurrent();
                calendarView2.scrollToCurrent();
                textView2.setText(String.valueOf(calendarView.getCurMonth()));
                textView1.setText(String.valueOf(calendarView.getCurYear()));
            }
        });
        floatbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                add_agenda=(ConstraintLayout)getLayoutInflater().inflate(R.layout.add_agenda,null);
                dialog.setTitle("新建日程");
                dialog.setMessage("请输入备注和日期");
                dialog.setView(add_agenda);
                dialog.setPositiveButton("确定",new addClick());
                dialog.setNegativeButton("取消",new cancleClick());
                dialog.create();
                dialog.show();
            }
        });
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                calendarView1.scrollToCalendar(calendar.getYear(),calendar.getMonth(),calendar.getDay());
                calendarView2.scrollToCalendar(calendar.getYear(),calendar.getMonth(),calendar.getDay());
                SharedPreferences userSettings= getSharedPreferences("database", 0);
                if(userSettings.contains(String.valueOf(calendar.getYear())+"年"+String.valueOf(calendar.getMonth())+"月"+String.valueOf(calendar.getDay())+"日"))
                {
                    textView_agenda.setText(userSettings.getString(String.valueOf(calendar.getYear())+ "年"+String.valueOf(calendar.getMonth())+"月"+String.valueOf(calendar.getDay())+"日","默认"));
                }
                else
                {
                    textView_agenda.setText("");
                }
            }
        });



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

    private class addClick implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {



           //String regex="\\d\\d\\d\\d年\\d\\d月\\d\\d日";
            //if(editText2.getText().toString().matches(regex))
            //{
                editText1=add_agenda.findViewById(R.id.editText1);
                editText2=add_agenda.findViewById(R.id.editText2);
                settings=getSharedPreferences("database", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=settings.edit();
                editor.putString(editText2.getText().toString(),editText1.getText().toString());
                editor.commit();
                dialog.dismiss();
            //}
            /*else
            {
                editText2.setText("");
                Toast.makeText(MainActivity.this,"请按照年月日格式输出",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }*/



        }
    }

    private class cancleClick implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
           dialog.dismiss();
        }
    }
}



