package com.chanhee.sojeeon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class SignUpActivity extends AppCompatActivity {
    EditText sub_id;
    EditText sub_pw;
    Button sub_SignUp;
    MainActivity mainActivity;
    String id;
    String pw;
    String tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sub_id = findViewById(R.id.ID);
        sub_pw = findViewById(R.id.PW);
        sub_SignUp = findViewById(R.id.check);

        sub_SignUp.setOnClickListener(view -> {
            id = sub_id.getText().toString();
            pw = sub_pw.getText().toString();

            SharedPreferences prefs = getSharedPreferences("save", 0);
            tmp = prefs.getString(id, ""); //엥에뮬해
            if (tmp != null) { //이게 뭔코드냐 ?
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(id, pw);
                editor.commit();

                Toast.makeText(SignUpActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            } else {
                sub_id.setError("이미 존재하는 아이디 입니다.");
            }

        });
    }
}
