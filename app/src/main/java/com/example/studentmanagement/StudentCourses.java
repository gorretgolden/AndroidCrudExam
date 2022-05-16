package com.example.studentmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentCourses extends AppCompatActivity {


    Button next;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list_view);

        db = new DbHelper(this);
        ListView lv = (ListView) findViewById(R.id.course_list);

        ArrayList<HashMap<String, String>> courseList = (ArrayList<HashMap<String, String>>) db.getCourses();
        ListAdapter adapter = new SimpleAdapter(StudentCourses.this, courseList, R.layout.course_rows,new String[]{"name","description","period"}, new int[]{R.id.name, R.id.cdescription, R.id.cperiod});
        lv.setAdapter(adapter);

        Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentCourses.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
