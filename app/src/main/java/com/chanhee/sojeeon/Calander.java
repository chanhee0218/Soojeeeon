package com.chanhee.sojeeon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class Calander extends AppCompatActivity {
   MaterialCalendarView materialCalendarView;
   String Date;
   EditText editText;
   Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander);
        materialCalendarView=findViewById(R.id.calanderview);
        editText = findViewById(R.id.edittxtt);
        Date=editText.getText().toString();
        button = findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Calander.this,TodoActivity.class);
                intent.putExtra("day", Date);
                startActivity(intent);
            }
        });


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if(selected){
                    Intent intent=new Intent(Calander.this,TodoActivity.class);
                    intent.putExtra("day", Date);
                    startActivity(intent);

                }
            }
        });
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(0)
                .setMinimumDate(CalendarDay.from(2019,0,1))
                .setMaximumDate(CalendarDay.from(2022,10,31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
    }
}
