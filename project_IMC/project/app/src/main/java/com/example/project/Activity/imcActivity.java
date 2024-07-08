package com.example.project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Helper.DBHelper;
import com.example.project.R;

public class imcActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc);
      //  getBundle();


    }
/*
    private void getBundle() {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText   taille = (EditText) findViewById(R.id.Taille);
                EditText  poids = (EditText) findViewById(R.id.poids);
                TextView results = (TextView) findViewById(R.id.textView8);

                int n1 = Integer.parseInt(taille.getText().toString());
                int n2 = Integer.parseInt(poids.getText().toString());
                int r1 = n2 / ((n1 * n1) / 10000);


                results.setText(r1);
            }
        });
        }

    */
    public void imc (View v){

        EditText taille = (EditText) findViewById(R.id.Taille);
        EditText poids = (EditText) findViewById(R.id.poids);
        TextView results = (TextView) findViewById(R.id.textView8);

        int n1 = Integer.parseInt(taille.getText().toString());
        int n2 = Integer.parseInt(poids.getText().toString());
        int r1 = n2/(n1 * n1 / 10000);

        results.setText("Votre IMC =" + r1);





    }

}