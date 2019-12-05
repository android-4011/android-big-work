package com.example.androidbigwork;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Button button2=(Button)findViewById(R.id.button2);
        Button button3=(Button)findViewById(R.id.button3);
        Button button4=(Button)findViewById(R.id.button4);
        button2.setOnClickListener( (View.OnClickListener) new button2_Click());
        button3.setOnClickListener( (View.OnClickListener) new button3_Click());
        button4.setOnClickListener( (View.OnClickListener) new button4_Click());
    }

    private class button2_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this,"当前已在月视图",Toast.LENGTH_SHORT).show();
        }
    }

    private class button3_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(i);
        }
    }

    private class button4_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }
}
