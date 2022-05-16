package com.example.productcrudproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginStudent extends AppCompatActivity {

    Button login;
    EditText reg, psd;
    DbHelper db;
    TextView noAccount, backtohome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_student);

        reg = findViewById(R.id.reg);
        psd = findViewById(R.id.psd);
        login = findViewById(R.id.login);
        noAccount = findViewById(R.id.noAccount);
        backtohome = findViewById(R.id.wlcm);
        db = new DbHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String student_regno = reg.getText().toString();
                String student_password = psd.getText().toString();


                //blank fields
                if(student_regno.equals("")  || student_password.equals("")  ){
                    Toast.makeText(LoginStudent.this, "Please provide all login credentials", Toast.LENGTH_SHORT).show();

                 }
                else{
                    //checking if this student exists
                    Boolean checkstudentlogin= db.checkStudentLogin(student_regno,student_password);

                    if(checkstudentlogin==true){


                        Toast.makeText(LoginStudent.this, "You have successfully logged into your account", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),StudentDashboard.class);
                        startActivity(intent);


                    }
                    else{
                        Toast.makeText(LoginStudent.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        //has no account

        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterStudent.class);
                startActivity(intent);
            }
        });


        backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
