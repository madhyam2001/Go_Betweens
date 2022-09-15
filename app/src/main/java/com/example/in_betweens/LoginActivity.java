package com.example.in_betweens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private TextView Register_move;
    private EditText Email_login;
    private EditText Password_login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Register_move=findViewById(R.id.register_move);
        Email_login=findViewById(R.id.email_login);
        Password_login=findViewById(R.id.password_login);


        Register_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

    }


}