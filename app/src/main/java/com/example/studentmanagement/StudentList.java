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

public class StudentList extends AppCompatActivity {


    Button next;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_students);

        db = new DbHelper(this);
        ListView lv = (ListView) findViewById(R.id.students_list);

        ArrayList<HashMap<String, String>> studentList = (ArrayList<HashMap<String, String>>) db.getStudents();
        ListAdapter adapter = new SimpleAdapter(StudentList.this, studentList, R.layout.students_list,new String[]{"regno","name","email","address"}, new int[]{R.id.name, R.id.email, R.id.address});
        lv.setAdapter(adapter);

        Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(StudentList.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
