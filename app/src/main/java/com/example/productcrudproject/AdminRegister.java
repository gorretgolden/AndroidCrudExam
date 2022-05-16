package com.example.productcrudproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminRegister extends AppCompatActivity {

    TextView adminLoginTxt;
    Button adminRegister;
    EditText  name, email,password1,password2;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_register);


        //targeting views
        db = new DbHelper(this);
        adminLoginTxt = findViewById(R.id.text_to_login);
        adminRegister = findViewById(R.id.adminRegister);
        name = (EditText) findViewById(R.id.rname);
        email = (EditText) findViewById(R.id.remail);
        password1 = (EditText) findViewById(R.id.rpass1);
        password2 = (EditText) findViewById(R.id.rpass2);


        //event handlers
        adminLoginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterStudent.class);
                startActivity(intent);
            }
        });

        //registering new admin
        adminRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String admin_name = name.getText().toString().trim();
                String admin_email = email.getText().toString().trim();
                String admin_password = password1.getText().toString().trim();
                String password_confirm= password2.getText().toString().trim();

                //blank fields
                if(admin_name.equals("")   || admin_email.equals("") || admin_password.equals("") || password_confirm.equals("")  ){
                    Toast.makeText(AdminRegister.this, "Please fill in all the registration fields", Toast.LENGTH_SHORT).show();

                }
                else{

                    //password confirmation
                    if(admin_password.equals(password_confirm)){


                          //check admin username
                            Boolean checkadminname = db.checkAminName(admin_name);

                            if(checkadminname==false){
                                //admin email

                                Boolean checkadminemail = db.checkAminEmail(admin_email);

                                if(checkadminemail==false){

                                    Boolean is_inserted = db.RegisterAmin(admin_email,admin_name,admin_password);
                                    if(is_inserted==true){
                                        Toast.makeText(AdminRegister.this, "Successfully created a new admin account", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(getApplicationContext(),AdminLogin.class);
//                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(AdminRegister.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(AdminRegister.this, "This admin email exists", Toast.LENGTH_SHORT).show();
                                }

                            }
                            else{
                                Toast.makeText(AdminRegister.this, "This username is already in use", Toast.LENGTH_SHORT).show();
                            }



                    }else{
                        Toast.makeText(AdminRegister.this, "Passwords don't match", Toast.LENGTH_SHORT).show();

                    }

                }


            }
        });



//        adminLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),StudentList.class);
//                startActivity(intent);
//            }
//        });
    }
}
