package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout logIn = findViewById(R.id.login);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, TimeActivity.class));
                Animatoo.animateCard(MainActivity.this);
            }
        });

    }

}
