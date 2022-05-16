package com.example.studentmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentUpdateProfile extends AppCompatActivity {
    Button updateProfile, backtodashboard;
    EditText email,password;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_update_profile);

        email = findViewById(R.id.uemail);
        password = findViewById(R.id.upassword);
        updateProfile = findViewById(R.id.updateaccount);
        backtodashboard = findViewById(R.id.backtodashboard);
        db = new DbHelper(this);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String student_email = email.getText().toString();
                String student_password = password.getText().toString();


                //blank fields
                if(student_email.equals("")  || student_password.equals("")  ){
                    Toast.makeText(StudentUpdateProfile.this, "Please provide all login credentials", Toast.LENGTH_SHORT).show();

                }
                else{
                    //checking if this student exists
                    Boolean checkstudentlogin= db.checkStudentLogin(student_email,student_password);

                    if(checkstudentlogin==true){


                        Toast.makeText(StudentUpdateProfile.this, "You have successfully logged into your account", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),StudentDashboard.class);
                        startActivity(intent);


                    }
                    else{
                        Toast.makeText(StudentUpdateProfile.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
