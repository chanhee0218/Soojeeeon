package com.chanhee.sojeeon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button talk,doing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        talk=findViewById(R.id.btnTalk);
        doing=findViewById(R.id.btnDo);
        doing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todo=new Intent(MainActivity.this,Calander.class);
                startActivity(todo);
            }
        });
        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TalkActivity.class);
                startActivity(intent);

            }
        });

    }
}
