package com.example.studentmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class AdminLogin extends Activity {

    EditText email,password;
    Button adminLogin;
    TextView backtohome;
    DbHelper db;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        adminLogin = (Button) findViewById(R.id.adminLogin);
        email = (EditText) findViewById(R.id.loginemail);
        password = (EditText) findViewById(R.id.loginPassword);
        backtohome = (TextView) findViewById(R.id.backtohome);
        db = new DbHelper(this);



        //login admin
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String admin_email = email.getText().toString();
                String admin_password = password.getText().toString();


                //blank fields
                if(admin_email.equals("")  || admin_password.equals("")  ){
                    Toast.makeText(AdminLogin.this, "Please provide all login credentials", Toast.LENGTH_SHORT).show();

                }
                else{
                    //checking if this student exists
                    Boolean checkadminlogincredentials= db.checkAdminLogin(admin_email,admin_password);

                    if(checkadminlogincredentials==true){


                        Toast.makeText(AdminLogin.this, "You have successfully logged into your account", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),AdminDashboard.class);
                        startActivity(intent);


                    }
                    else{
                        Toast.makeText(AdminLogin.this, "Authentication failed, wrong admin credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



        backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home_page = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home_page);

            }
        });

//
//
//        dashboard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
//                startActivity(intent);
//
//            }
//        });
    }
}
