package com.example.remindme;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeActivity extends Activity {
    private final String CHANNEL_ID = "CHANNEL_ID";
    private TimePicker timePicker;
    int hour, min;
    ImageButton next;
    TextView timeDisp, str;
    String format = "";
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        layout = findViewById(R.id.relative_layout);
        layout.setBackgroundResource(R.drawable.gradient_animation);
        AnimationDrawable animDrawable = (AnimationDrawable) layout.getBackground();
        animDrawable.start();

        timePicker = findViewById(R.id.timepicker);

        hour = timePicker.getHour();
        min = timePicker.getMinute();
        String time = hour + ":" + min;

        timeDisp = findViewById(R.id.timedisp);
        next = findViewById(R.id.nextbtn);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        final String currentDateandTime = sdf.format(new Date());

        final ImageButton btn = findViewById(R.id.btnSelect);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timePicker.getVisibility() == View.INVISIBLE)
                timePicker.setVisibility(View.VISIBLE);

                else
                    timePicker.setVisibility(View.INVISIBLE);

            }
        });
        timeDisp.setText(time);

//        checkTime(time, currentDateandTime);
        showTime(hour, min);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = timePicker.getHour();
                min = timePicker.getMinute();
                String time = hour + ":" + min;
                timeDisp.setText(time);
//                checkTime(time, currentDateandTime);
                showTime(hour, min);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeActivity.this, EnterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

//        timeDisp.setText(new StringBuilder().append(hour).append(" : ").append(min).append(" ").append(format));

    }

    public void generateNotification(){

    }

    private void createNotificationChannel() {
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (notificationManager == null){
            notificationManager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        }
        else
        notificationManager.createNotificationChannel(channel);
    }
//    void checkTime(String a, String b){
//        if (a.equals(b)){
//            Toast.makeText(this, "Equal", Toast.LENGTH_LONG).show();
//            generateNotification();
//        }
//        else
//            Toast.makeText(this, "Not Equal", Toast.LENGTH_LONG).show();
//    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}