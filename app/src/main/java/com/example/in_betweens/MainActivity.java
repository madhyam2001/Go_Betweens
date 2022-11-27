package com.example.in_betweens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread= new Thread()
        {
          @Override
          public void run()
          {
              try
              {
                  sleep(4000);

              }
              catch (Exception e)
              {
                  e.printStackTrace();

              }
              finally
              {
                  Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
                  startActivity(loginIntent);


              }
          }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}