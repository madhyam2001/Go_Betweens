package com.example.in_betweens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextView Register_move;
    private EditText Email_login;
    private EditText Password_login;
    private Button Login_btn;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();


        Register_move=findViewById(R.id.register_move);
        Email_login=findViewById(R.id.email_login);
        Password_login=findViewById(R.id.password_login);
        Login_btn=findViewById(R.id.login_btn);
        loadingbar=new ProgressDialog(this);


        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=Email_login.getText().toString();
                String password=Password_login.getText().toString();
                SigninUser(email, password);
            }
        });



        Register_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });



    }

    private void SigninUser(String email, String password)
    {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(LoginActivity.this, "Email Field is Empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(LoginActivity.this, "Enter any Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar.setTitle("Signing in....");
            loadingbar.setMessage("Please Wait, While we are signing you in.....");
            loadingbar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this, "Logged in Successfully....", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Sorry, Please Try Again....", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }

                }
            });

        }

    }


}