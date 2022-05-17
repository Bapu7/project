package com.example.user_information;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class Registration_Activty extends AppCompatActivity {
    TextView time,date,resignin;
    RadioButton male,female;
    RadioGroup radioGroup;
    Button registerbtn;
    EditText username,email,mobile,password,rpassword;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activty);
        time=findViewById(R.id.time);
        date=findViewById(R.id.date);
        radioGroup=findViewById(R.id.radiobutton);
        resignin=findViewById(R.id.resignin);
        registerbtn = findViewById(R.id.registerbtn);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        rpassword = findViewById(R.id.rpassword);
        mAuth = FirebaseAuth.getInstance();

        resignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Registration_Activty.this,Login_Activty.class);
                startActivity(intent);
                finish();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                int yy = c.get(Calendar.YEAR);
                int mm = c.get(Calendar.MONTH);
                int dd = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(Registration_Activty.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = "Date:" + String.valueOf(day) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year);
                        time.setText(date);

                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(Registration_Activty.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int myHour, int myMinute) {

                        String time = "Time:" + String.valueOf("0") + String.valueOf(myHour) + ":" + String.valueOf(myMinute) + String.valueOf("");
                        date.setText(time);

                    }
                }, hour, minute, false);
                timePicker.show();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                male = findViewById(R.id.male);
                female = findViewById(R.id.female);
                if (male.isChecked()) {
                    Toast.makeText(Registration_Activty.this, "male", Toast.LENGTH_SHORT).show();

                } else if (female.isChecked()) {
                    Toast.makeText(Registration_Activty.this, "female", Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nam = username.getText().toString();
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String rpass = rpassword.getText().toString();

                if (nam.isEmpty()) {
                    username.setError("Please Enter your name!!");
                } else if (mail.isEmpty()) {
                    email.setError("enter your email");
                } else if (pass.isEmpty()) {
                    password.setError("please enter password");
                } else if (rpass.isEmpty()) {
                    rpassword.setError("re-enter password");
                } else if (rpass.isEmpty()) {
                    mobile.setError("re-enter Number");

                } else {
                    mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(Registration_Activty.this, "Registration Failed..", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Registration_Activty.this, "Registration Successful..", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Registration_Activty.this, Login_Activty.class);
                                startActivity(i);
                            }
                        }
                    });

                }
            }
        });

    }
}