package com.example.remindme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remindme.Model.ItemAdapter;
import com.example.remindme.Model.ReminderAdapter;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EnterActivity extends Activity {
    boolean click;
    int i = 1;
    List<ItemAdapter> list = new ArrayList<>();
    ReminderAdapter reminderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        RelativeLayout relativeLayout = findViewById(R.id.relative_layout);

        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setImage(R.drawable.book_1);
        itemAdapter.setTitle("Study AM - IV");
        itemAdapter.setDescription("Unit 1");
        itemAdapter.setPriority("High");
        list.add(itemAdapter);

        itemAdapter = new ItemAdapter();
        itemAdapter.setImage(R.drawable.book_2);
        itemAdapter.setTitle("Study TOC");
        itemAdapter.setDescription("Unit 1");
        itemAdapter.setPriority("Medium");
        list.add(itemAdapter);

        itemAdapter = new ItemAdapter();
        itemAdapter.setImage(R.drawable.book_3);
        itemAdapter.setTitle("Watch Friends");
        itemAdapter.setDescription("Season 2");
        itemAdapter.setPriority("Low");
        list.add(itemAdapter);


        final TextView txt = findViewById(R.id.date);
        ImageButton nextdate = findViewById(R.id.dateNext);
        ImageButton prevdate = findViewById(R.id.datePrevious);

        //current date
        SimpleDateFormat sdf = new SimpleDateFormat("E dd, MMM");
        final String todayDate = sdf.format(new Date());
        txt.setText(todayDate);

        prevdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String prev = prevDate(todayDate, i);
                    i++;
                    txt.setText(prev);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        nextdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String next = nextDate(todayDate,i);
                    i++;
                    txt.setText(next);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });


        // bottom navigation bar
        BubbleNavigationConstraintView bubblenavigation = findViewById(R.id.bottom_navigation_constraint);

        bubblenavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {

                if (position == 0)
                    Toast.makeText(EnterActivity.this, "Viewing the Same", Toast.LENGTH_LONG).show();

                else if(position == 1)
                    Toast.makeText(EnterActivity.this, "Opening Alarm", Toast.LENGTH_LONG).show();

                else if(position == 2)
                    Toast.makeText(EnterActivity.this, "Adding Item", Toast.LENGTH_LONG).show();

                else if(position == 3)
                    Toast.makeText(EnterActivity.this, "Opening Checkist", Toast.LENGTH_LONG).show();

                else if(position == 4)
                    Toast.makeText(EnterActivity.this, "Opening Profile", Toast.LENGTH_LONG).show();
            }

        });
    }
        String nextDate(String curDate, int no) throws ParseException {

        final SimpleDateFormat format = new SimpleDateFormat("E dd, MMM");
            final Date date = format.parse(curDate);
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, no);
            return format.format(calendar.getTime());
        }

    String prevDate(String curDate, int no) throws ParseException {

        final SimpleDateFormat format = new SimpleDateFormat("E dd, MMM");
        final Date date = format.parse(curDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -no);
        return format.format(calendar.getTime());
    }
}
