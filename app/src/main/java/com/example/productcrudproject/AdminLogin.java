package com.example.productcrudproject;

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
    TextView text_to_register;
    DbHelper db;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        adminLogin = (Button) findViewById(R.id.adminLogin);
        email = (EditText) findViewById(R.id.loginemail);
        password = (EditText) findViewById(R.id.loginPassword);
        text_to_register = (TextView) findViewById(R.id.text_to_register);
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
                        Toast.makeText(AdminLogin.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



        text_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register_page = new Intent(getApplicationContext(), AdminRegister.class);
                startActivity(intent_register_page);

            }
        });



//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_register_page = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent_register_page);
//
//            }
//        });
    }
}
