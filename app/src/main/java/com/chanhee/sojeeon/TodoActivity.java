package com.chanhee.sojeeon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {
    EditText editTodo;
    Button btncheck;
    String Do;
    String WhenDate;
    ListView listView;
    String TAG;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference=firebaseDatabase.getReference();
    private ChildEventListener childEventListener;
    private ArrayAdapter<String> arrayAdapter;
    List<Object> Array=new ArrayList<Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        editTodo=findViewById(R.id.todoEdit);
        btncheck=findViewById(R.id.checkTodo);
        Do=editTodo.getText().toString();
        listView=findViewById(R.id.ListView);

        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(arrayAdapter);
//        initDataBase();
        databaseReference=firebaseDatabase.getReference("message");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayAdapter.clear();
                for(DataSnapshot messageData:dataSnapshot.getChildren()){
                    String msg=messageData.getValue(String.class);

                    Array.add(msg);
                    arrayAdapter.add(msg);
                }
                arrayAdapter.notifyDataSetChanged();
                listView.setSelection(arrayAdapter.getCount()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read Value", databaseError.toException());
            }
        });


        Intent intent=getIntent();
        WhenDate=intent.getStringExtra("day");
        Toast.makeText(this, ""+WhenDate, Toast.LENGTH_SHORT).show();

        btncheck.setOnClickListener(v ->
                databaseReference.child(WhenDate).setValue(Do)
        );

    }
    private void initDataBase(){
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        databaseReference.child(WhenDate).setValue(Do);
        childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addChildEventListener(childEventListener);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(childEventListener);
    }

}
