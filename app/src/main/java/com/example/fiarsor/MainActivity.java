package com.example.fiarsor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button SignIn,SignUp;
    TextView txtSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignIn= (Button)findViewById(R.id.SignIn);
        SignUp= (Button)findViewById(R.id.SignUp);
        txtSlogan= (TextView)findViewById(R.id.txtSlogan);


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn  = new Intent(MainActivity.this,SignIn.class);
                startActivity(signIn);

            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent signUp  = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);

            }
        });
    }
}
