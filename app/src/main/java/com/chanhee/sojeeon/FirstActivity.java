package com.chanhee.sojeeon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {
    Button talk,doing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        talk=findViewById(R.id.btnTalk);
        doing=findViewById(R.id.btnDo);
        doing.setOnClickListener(v -> {
            Intent todo=new Intent(FirstActivity.this,Calander.class);
            startActivity(todo);
        });
        talk.setOnClickListener(v -> {
            Intent intent=new Intent(FirstActivity.this,TalkActivity.class);
            startActivity(intent);

        });

    }
}
