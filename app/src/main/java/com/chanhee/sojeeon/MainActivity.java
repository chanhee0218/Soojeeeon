package com.chanhee.sojeeon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
        private FirebaseAuth mAuth;
        EditText ID,PW;
        Button Login,Join;
        String id,pw;




    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            FirebaseApp.initializeApp(getApplicationContext());
            mAuth = FirebaseAuth.getInstance();
            ID = findViewById(R.id.ID);
            PW = findViewById(R.id.PW);
            Login = findViewById(R.id.Login);
            Join = findViewById(R.id.Join);

            Login.setOnClickListener(v -> {

                        id = ID.getText().toString();
                        pw = PW.getText().toString();
                mAuth.createUserWithEmailAndPassword(id,pw)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                finish();
                                FirebaseUser user = mAuth.getCurrentUser();

                            } else {
                                Toast.makeText(MainActivity.this, "Fail Login", Toast.LENGTH_SHORT).show();
                            }
                        });

            });
            Join.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);

            });
    }
}
