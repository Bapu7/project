package com.example.user_information;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activty extends AppCompatActivity {
    TextView create;
    ImageView back;
    FirebaseAuth mAuth;
    EditText loginemail, loginpassword, loginrpassword;
    Button signinbtn;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);
        mAuth = FirebaseAuth.getInstance();

        firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            startActivity(new Intent(Login_Activty.this, MainActivity.class));
        }

        loginemail = findViewById(R.id.loginemail);
        loginpassword = findViewById(R.id.loginpassword);
        loginrpassword = findViewById(R.id.loginrpassword);
        signinbtn = findViewById(R.id.signinbtn);
        create = findViewById(R.id.create);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activty.this, Registration_Activty.class);
                startActivity(intent);
                finish();

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activty.this, Registration_Activty.class);
                startActivity(intent);
                finish();

            }
        });
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = loginemail.getText().toString();
                String passw = loginpassword.getText().toString();
                String rpassw = loginrpassword.getText().toString();

                if (mail.isEmpty()) {
                    loginemail.setError("enter email");
                } else if (passw.isEmpty()) {
                    loginpassword.setError("enter your password");
                } else if (rpassw.isEmpty()) {
                    loginrpassword.setError("Check Password");
                } else {
                    mAuth.signInWithEmailAndPassword(mail, passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login_Activty.this, "Login Failed.....", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Login_Activty.this, "Login Success...", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Login_Activty.this, MainActivity.class);
                                startActivity(i);

                            }

                        }

                    });
                }


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

