package com.example.androidbigwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        Button button2=(Button)findViewById(R.id.button2);
        Button button3=(Button)findViewById(R.id.button3);
        Button button4=(Button)findViewById(R.id.button4);
        button2.setOnClickListener( (View.OnClickListener) new Main2Activity.button2_Click());
        button3.setOnClickListener( (View.OnClickListener) new Main2Activity.button3_Click());
        button4.setOnClickListener( (View.OnClickListener) new Main2Activity.button4_Click());
    }
    private class button2_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Main2Activity.this,MainActivity.class);
            startActivity(i);
        }
    }

    private class button3_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Toast.makeText(Main2Activity.this,"当前已在周视图",Toast.LENGTH_SHORT).show();
        }
    }

    private class button4_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }
}
