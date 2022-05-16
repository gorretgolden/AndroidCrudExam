package com.example.studentmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminAddCourse extends AppCompatActivity {


    Button adminAddCourse,back;
    EditText name, description,period;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_courses);


        //targeting views
        db = new DbHelper(this);
        adminAddCourse = findViewById(R.id.adminAddCourse);
        back = findViewById(R.id.cback);
        name = (EditText) findViewById(R.id.cname);
        description = (EditText) findViewById(R.id.cdescription);
        period = (EditText) findViewById(R.id.cperiod);

        adminAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_name = name.getText().toString().trim();
                String course_description = description.getText().toString().trim();
                String course_period = period.getText().toString().trim();


                //blank fields
                if(course_name.equals("")   || course_description.equals("") || course_period.equals("") ){
                    Toast.makeText(AdminAddCourse.this, "Please fill in all the course fields", Toast.LENGTH_SHORT).show();

                }
                else{

                        //check course name
                        Boolean checkcoursename = db.checkCourseName(course_name);

                        if(checkcoursename==false){
                            //admin email

                            Boolean checkcoursedescription = db.checkCourseDescription(course_description);

                            if(checkcoursedescription==false){

                                Boolean is_inserted = db.AminAddNewCourse(course_name,course_description,course_period);
                                if(is_inserted==true){
                                    Toast.makeText(AdminAddCourse.this, "Successfully created a new student course", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),AdminDashboard.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(AdminAddCourse.this, "Failed to add new course", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(AdminAddCourse.this, "This course description exists", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(AdminAddCourse.this, "This course name is already in use", Toast.LENGTH_SHORT).show();
                        }



                    }
                }


        });


    }
}
