package com.chanhee.sojeeon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText main_id;
    EditText main_pw;
    ArrayList<String> id_array = new ArrayList<String>();
    ArrayList<String> pw_array = new ArrayList<String>();
    String result;

    Button Login;
    Button Join;
    String id;
    String pw;
    int ErrorCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_id = findViewById(R.id.ID);
        main_pw = findViewById(R.id.PW);
        Join = findViewById(R.id.Join);
        Login = findViewById(R.id.Login);
        Join.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
        });

        Login.setOnClickListener(view -> {
            SharedPreferences prefs = getSharedPreferences("save",0);
            id = main_id.getText().toString();

            pw = main_pw.getText().toString();

            result = prefs.getString(id,"");

                if (!result.equals(pw)){
                    Toast.makeText(MainActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                }
                else if (result.equals(pw)){
                    Toast.makeText(MainActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this, FirstActivity.class);
                    startActivity(intent);

                }else if(result==null){
                    Toast.makeText(this, "다시 입력 하세요", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(MainActivity.this, "회원정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }

        });
    }
}
