package com.example.in_betweens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    private Button Carrier_btn;
    private Button Customer_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Carrier_btn=findViewById(R.id.carrier);
        Customer_btn=findViewById(R.id.customer);

        Carrier_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent carrier_Intent= new Intent(ChooseActivity.this,CarrierMapActivity.class);
                startActivity(carrier_Intent);
            }
        });
        Customer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customer_Intent= new Intent(ChooseActivity.this,Customer_MapsActivity.class);
                startActivity(customer_Intent);
            }
        });




    }
}