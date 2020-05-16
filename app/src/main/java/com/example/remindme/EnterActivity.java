package com.example.remindme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    int i = 1;
    List<ItemAdapter> list = new ArrayList<>();
    ReminderAdapter reminderAdapter;
    String taskName, description, priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        RecyclerView recyclerview = findViewById(R.id.recyclerView);

//        recyclerview.setVisibility();
        Intent intent = getIntent();

         taskName = intent.getStringExtra("Task_Name");
        description = intent.getStringExtra("Description");
        priority = intent.getStringExtra("Priority");

        recyclerview(taskName, description, priority);

        reminderAdapter = new ReminderAdapter(list, this);
        recyclerview.setAdapter(reminderAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        reminderAdapter.notifyDataSetChanged();


        final TextView txt = findViewById(R.id.date);
        ImageButton nextdate = findViewById(R.id.dateNext);
        ImageButton prevdate = findViewById(R.id.datePrevious);

        //current date
        SimpleDateFormat sdf = new SimpleDateFormat("E dd, MMM");
        final String todayDate = sdf.format(new Date());
        txt.setText(todayDate);

        //today's date
        TextView today = findViewById(R.id.txt);
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(todayDate);
            }
        });

        //prev date
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

        //next date
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

                else if(position == 2){
                    Intent intent = new Intent(EnterActivity.this, AddActivity.class);
                    startActivity(intent);
                }

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

    void recyclerview(String name, String description, String priority){

        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setImage(R.drawable.summertime);
        itemAdapter.setTitle(name);
        itemAdapter.setDescription(description);
        itemAdapter.setPriority(priority);
        list.add(itemAdapter);

//        itemAdapter = new ItemAdapter();
//        itemAdapter.setImage(R.drawable.book_2);
//        itemAdapter.setTitle("Study TOC");
//        itemAdapter.setDescription("Unit 1");
//        itemAdapter.setPriority("Medium");
//        list.add(itemAdapter);
//
//        itemAdapter = new ItemAdapter();
//        itemAdapter.setImage(R.drawable.book_3);
//        itemAdapter.setTitle("Watch Friends");
//        itemAdapter.setDescription("Season 2");
//        itemAdapter.setPriority("Low");
//        list.add(itemAdapter);
//

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, TimeActivity.class);
        startActivity(intent);
    }
}
