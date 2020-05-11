package com.example.remindme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleDateFormat date = new SimpleDateFormat("k");
        final String t = date.format(new Date());

        if (checkTime(t))
            setContentView(R.layout.activity_main_night);

        else  setContentView(R.layout.activity_main);

        RelativeLayout logIn = findViewById(R.id.login);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, TimeActivity.class));
                Animatoo.animateCard(MainActivity.this);
            }
        });

    }

    boolean checkTime(String t){
        return (t.equals("20") || t.equals("21") || t.equals("22") || t.equals("23") || t.equals("24") || t.equals("1") || t.equals("2")
                || t.equals("3") || t.equals("4") || t.equals("5"));
    }

}
