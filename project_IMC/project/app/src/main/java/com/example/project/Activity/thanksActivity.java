package com.example.project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class thanksActivity extends AppCompatActivity {
    private TextView button;
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        textView3 = findViewById(R.id.textView3);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        textView3.setText(name);
        //textView12.setText(email);
        initView();
        getBundle();



}

    private void getBundle() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(thanksActivity.this, imcActivity.class));
            }
        });
    }

    private void initView() {
        button = findViewById(R.id.button);

    }


}
