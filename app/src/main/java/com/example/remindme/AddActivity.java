package com.example.remindme;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

public class AddActivity extends Activity {

    Button low, med, high, back;
    EditText date, taskname, desc;
    DatePickerDialog dialog;
    TextView txt;
    RelativeLayout rel;
    String prior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        date = findViewById(R.id.dateInput);

        low = findViewById(R.id.priorLow);
        med = findViewById(R.id.priorMed);
        high = findViewById(R.id.priorHigh);

        txt = findViewById(R.id.priorDisp);
        desc = findViewById(R.id.descInput);
        taskname = findViewById(R.id.taskName);

        final String description = desc.getText().toString();
        final String taskName = taskname.getText().toString();
        final String dateStr = date.toString();


        back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, EnterActivity.class);
                startActivity(intent);
            }
        });

        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("High");
            }
        });

        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("Medium");
            }
        });

        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("Low");
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                dialog = new DatePickerDialog(AddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + " " + (monthOfYear + 1) + "," + year);
                            }
                        }, year, month, day);
                dialog.show();
            }
        });

        rel = findViewById(R.id.createTask);
        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, EnterActivity.class);
                intent.putExtra("Task_Name",taskName);
                intent.putExtra("Date",dateStr);
                intent.putExtra("Priority", prior);
                intent.putExtra("Description",description);
                startActivity(intent);
            }
        });
    }

    void setText(String a){
        if (a.equals("Low")) {
            txt.setTextColor(Color.parseColor("#F08080"));

        }

        else if (a.equals("Medium")) {
            txt.setTextColor(Color.parseColor("#90EE90"));

        }

        else if (a.equals("High")){
            txt.setTextColor(Color.parseColor("#000"));
        }
        prior = a;
        txt.setText(a);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddActivity.this, EnterActivity.class);
        startActivity(intent);
    }
}
