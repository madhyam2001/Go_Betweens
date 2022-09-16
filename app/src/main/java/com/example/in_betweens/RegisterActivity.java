package com.example.in_betweens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private TextView Login_move;
    private EditText Email_register;
    private EditText Password_register;
    private Button Register_btn;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();


        Login_move=findViewById(R.id.login_move);
        Email_register=findViewById(R.id.email_register);
        Password_register=findViewById(R.id.password_register);
        Register_btn=findViewById(R.id.login_btn);
        loadingbar=new ProgressDialog(this);

        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=Email_register.getText().toString();
                String password=Password_register.getText().toString();

                RegisterUser(email,password);
            }
        });


        Login_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(loginIntent);
            }
        });

    }

    private void RegisterUser(String email,String password )
    {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(RegisterActivity.this, "Email Field is Empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(RegisterActivity.this, "Enter any Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar.setTitle("User's Registration");
            loadingbar.setMessage("Please Wait, While we are registering your data");
            loadingbar.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(RegisterActivity.this, "Registered Successfully....", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Registration Unsuccessful, Please Try Again....", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }

                }
            });

        }
    }

}