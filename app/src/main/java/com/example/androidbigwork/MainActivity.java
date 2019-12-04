package com.example.androidbigwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0,1,0,"月视图");
        menu.add(0,2,1,"周视图");

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if(item.getItemId()==1)
        {

            Toast toast=Toast.makeText(MainActivity.this,"已经在月视图",Toast.LENGTH_SHORT);
            return true;
        }
        else if(item.getItemId()==2)
        {
            Intent intentre=new Intent(MainActivity.this,Main2Activity.class);
            startActivityForResult(intentre, 1);
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }


    }
}
