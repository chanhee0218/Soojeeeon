package com.chanhee.sojeeon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    Button check;
    EditText ID,PW;
    private String pw;
    private String id;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        check = findViewById(R.id.check);
        ID = findViewById(R.id.ID);
        PW = findViewById(R.id.PW);


        check.setOnClickListener(view -> {
            pw =PW.getText().toString();
            id = ID.getText().toString();

            mAuth.createUserWithEmailAndPassword(id,pw).addOnCompleteListener(this,task -> {
                if (task.isSuccessful()) {
                    finish();

                }
                else{
                    Toast.makeText(this, "RegisterFailed", Toast.LENGTH_SHORT).show();
            }
            });
        });
    }


}
