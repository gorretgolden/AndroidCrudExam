package com.example.studentmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RegisterStudent extends AppCompatActivity {

    Button register ;
    EditText regno, name, email,address,password1,password2;
    DbHelper db;
    TextView backtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_student);

        name = (EditText) findViewById(R.id.name);
        regno = (EditText) findViewById(R.id.regno);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        password1 = (EditText) findViewById(R.id.password1);
        password2 = (EditText) findViewById(R.id.password2);
        register = (Button) findViewById(R.id.register);
        backtxt = (TextView) findViewById(R.id.backtxt);
        db = new DbHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {

                                       String student_regno = regno.getText().toString().trim();
                                       String student_name = name.getText().toString().trim();
                                       String student_email = email.getText().toString().trim();
                                       String student_address = address.getText().toString().trim();
                                       String student_password = password1.getText().toString().trim();
                                       String password_confirm= password2.getText().toString().trim();



                                       //blank fields
                                       if(student_name.equals("")  || student_regno.equals("") || student_email.equals("") || student_address.equals("") || student_password.equals("") || password_confirm.equals("")  ){
                                           Toast.makeText(RegisterStudent.this, "Please fill in all the registration fields", Toast.LENGTH_SHORT).show();

                                       }
                                       else{

                                           //password confirmation
                                           if(student_password.equals(password_confirm)){

                                               Boolean checkstudentregno = db.checkStudentReg(student_regno);
                                               if(checkstudentregno==false){

                                                   Boolean checkstudentname = db.checkStudentName(student_name);

                                                   if(checkstudentname==false){

                                                       Boolean checkstudentemail = db.checkStudentEmail(student_email);

                                                       if(checkstudentemail==false){

                                                           Boolean is_inserted = db.RegisterStudent(student_regno,student_name,student_email,student_address,student_password);
                                                           if(is_inserted==true){
                                                               Toast.makeText(RegisterStudent.this, "A new student was registered successfully", Toast.LENGTH_SHORT).show();
                                                               Intent intent = new Intent(getApplicationContext(),LoginStudent.class);
                                                               startActivity(intent);
                                                           }
                                                           else{
                                                               Toast.makeText(RegisterStudent.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                                           }
                                                       }
                                                       else{
                                                           Toast.makeText(RegisterStudent.this, "This student email exists", Toast.LENGTH_SHORT).show();
                                                       }

                                                   }
                                                   else{
                                                       Toast.makeText(RegisterStudent.this, "The student name exists", Toast.LENGTH_SHORT).show();
                                                   }


                                               }
                                               else{
                                                   Toast.makeText(RegisterStudent.this, "This student number exists", Toast.LENGTH_SHORT).show();
                                               }



                                           }else{
                                               Toast.makeText(RegisterStudent.this, "Passwords dont match", Toast.LENGTH_SHORT).show();

                                           }

                                       }
                                   }
                               });



        backtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminDashboard.class);
                startActivity(intent);
            }
        });





    }
}
