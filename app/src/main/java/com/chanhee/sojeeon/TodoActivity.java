package com.chanhee.sojeeon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TodoActivity extends AppCompatActivity {
    EditText editTodo;
    Button btncheck;
    String Do;
    String WhenDate;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference=firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        editTodo=findViewById(R.id.todoEdit);
        btncheck=findViewById(R.id.checkTodo);
        Do=editTodo.getText().toString();


        Intent intent=getIntent();
        WhenDate=intent.getStringExtra("day");
        Toast.makeText(this, ""+WhenDate, Toast.LENGTH_SHORT).show();

        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(WhenDate).push().setValue(Do);

            }
        });

    }
}
