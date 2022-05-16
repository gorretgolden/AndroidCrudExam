package com.example.studentmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminDashboard extends Activity {
    TextView textViewNewStudent,textViewAddCourses,textView23,textViewAddedCourse;
    ImageView backB;
    Button viewStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard);

        textViewNewStudent = findViewById(R.id.textViewNewStudent);
        textViewAddCourses = findViewById(R.id.textViewAddCourses);
        backB = findViewById(R.id.backB);
        viewStudents = findViewById(R.id.todoB);
        textView23 = findViewById(R.id.textView23);
        textViewAddedCourse = findViewById(R.id.textViewAddedCourses);

        //courses
        textViewAddedCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentCourses.class);
                startActivity(intent);

            }
        });

        //new admin
        textView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminRegister.class);
                startActivity(intent);

            }
        });
        //all students

        viewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentList.class);
                startActivity(intent);

            }
        });

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(intent);

            }
        });

        textViewNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterStudent.class);
                startActivity(intent);

            }
        });
        textViewAddCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminAddCourse.class);
                startActivity(intent);

            }
        });



    }
}
