package com.chanhee.sojeeon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class TalkActivity extends AppCompatActivity {
    String ChatName,ChatScript;
    ListView listView;
    Button chatbtn;
    EditText talkEdit,who;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("message");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        listView=findViewById(R.id.talkList);
        chatbtn=findViewById(R.id.btnTalk);
        talkEdit=findViewById(R.id.editTalk);
        who=findViewById(R.id.who);
        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(talkEdit==null&&who==null){
                    Toast.makeText(TalkActivity.this, "입력 양식을 확인 하십시오", Toast.LENGTH_SHORT).show();

                }
                ChatDTO chatDTO=new ChatDTO(ChatName,who.getText().toString());
                databaseReference.child("chat").child(ChatName).push().setValue(chat);
                who.setText("");
            }
        });
    }
    private void addMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter){
        ChatDTO chatDTO=dataSnapshot.getValue(ChatDTO.class);
        adapter.add(chatDTO.getUserName()+":"+ chatDTO.getMessage());

    }
    private  void removeMesaage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter){
        ChatDTO chatDTO=dataSnapshot.getValue(ChatDTO.class);
        adapter.remove(chatDTO.getUserName()+":"+chatDTO.getMessage());
    }
    private void openChat(String chatName){
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1);
        databaseReference.child("chat").child(chatName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                addMessage(dataSnapshot,adapter);
                Log.e("Log", "s"+s);
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
        })

        )
    }
}
