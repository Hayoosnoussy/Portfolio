package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.Helper.DBHelper;
import com.example.project.R;

public class IntroActivity extends AppCompatActivity {
    Button loginBtn,signupBtn;
    EditText username,password;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Password);
        loginBtn=(Button) findViewById(R.id.loginbtn);
        signupBtn=(Button) findViewById(R.id.registerbtn);
        DB = new DBHelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
            if (user.equals("")||pass.equals(""))
                Toast.makeText(IntroActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                if(checkuserpass==true) {
                    Toast.makeText(IntroActivity.this, "signin successufull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(IntroActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }

            }
            }
        });



        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}