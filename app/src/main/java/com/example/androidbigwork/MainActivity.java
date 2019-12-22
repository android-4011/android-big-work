package com.example.androidbigwork;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.haibin.calendarview.CalendarView;
import com.haibin.calendarview.MonthView;



public class MainActivity extends AppCompatActivity {
    /*
    CalendarView calendarView;
    TextView textView2;
    TextView textView1;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        textView1 = findViewById(R.id.textView1);//年份
        textView2 = findViewById(R.id.textView2);//月份

        calendarView = findViewById(R.id.calendarView);
        textView1.setText(String.valueOf(calendarView.getCurYear()));
        textView2.setText(String.valueOf(calendarView.getCurMonth()));

        calendarView.setOnYearChangeListener(new CalendarView.OnYearChangeListener() {
            @Override
            public void onYearChange(int year) {
                textView1.setText(String.valueOf(year));
            }
        });

        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                textView2.setText(String.valueOf(month));
            }
        });
        */
    }
}
