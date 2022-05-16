package com.example.productcrudproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button next,view,studentpage;
    Button admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        next = findViewById(R.id.next);
//        view = findViewById(R.id.view);
        admin = findViewById(R.id.adminpage);
        studentpage = findViewById(R.id.studentpage);

        //student
        studentpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterStudent.class);
                startActivity(intent);
            }
        });



        //event handlers
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),RegisterStudent.class);
//                startActivity(intent);
//            }
//        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminRegister.class);
                startActivity(intent);
            }
        });

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),StudentList.class);
//                startActivity(intent);
//            }
//        });
    }
}