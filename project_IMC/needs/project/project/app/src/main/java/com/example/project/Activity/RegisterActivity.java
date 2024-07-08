package com.example.project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Helper.DBHelper;
import com.example.project.R;

public class RegisterActivity extends AppCompatActivity  {
    EditText username ,password , repassword, email;
    Button signup,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Password);
        repassword = (EditText) findViewById(R.id.Repassword);
        email = (EditText) findViewById(R.id.Email);
        signin = (Button) findViewById(R.id.loginbtn);
        signup = (Button) findViewById(R.id.registerbtn);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String mail = email.getText().toString();

                if (user.equals("")||pass.equals("")||repass.equals("")||mail.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all Fields", Toast.LENGTH_SHORT).show();
                else
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        Boolean isValidEmail = DB.isValidEmail(mail);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user, pass, mail);
                            if (isValidEmail==true){
                                if(insert==true){
                                    Toast.makeText(RegisterActivity.this, "Registred successfully !", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),IntroActivity.class);
                                    startActivity(intent);
                                }else
                                    {
                                Toast.makeText(RegisterActivity.this, "Registred failed !", Toast.LENGTH_SHORT).show();
                            }
                            }else{
                                Toast.makeText(RegisterActivity.this, "Email is not verified !", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "User already exists please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                else {
                        Toast.makeText(RegisterActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),IntroActivity.class);
                startActivity(intent);
            }
        });

    }




}
