package com.example.androidbigwork;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Titlelayout extends ConstraintLayout {

    public Titlelayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.title_bar, this);
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

        }
    }

    private class button3_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }

    private class button4_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }
}
