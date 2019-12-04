package com.example.androidbigwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
            Intent intentre=new Intent(Main2Activity.this,Main2Activity.class);
            startActivityForResult(intentre, 1);
            return true;

        }
        else if(item.getItemId()==2)
        {
            Toast toast=Toast.makeText(Main2Activity.this,"已经在周视图",Toast.LENGTH_SHORT);
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }


    }
}
