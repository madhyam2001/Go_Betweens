package com.example.in_betweens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.in_betweens.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseActivity extends AppCompatActivity {

    private Button Carrier_btn;
    private Button Customer_btn;
    private Button logout_btn;
//    ActivityMainBinding binding;
//    String loc;
//    FirebaseDatabase db;
//    DatabaseReference reference;
//    Button d,a;
//    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
//        binding=ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        String userID= FirebaseAuth.getInstance().getCurrentUser().getUid();
//        logout_btn=findViewById(R.id.logout);

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
//        logout_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!userID.isEmpty()){
//                    deleteloc(userID);
//                }
//            }
//        });




    }

//    private void deleteloc(String userID) {
//        reference=FirebaseDatabase.getInstance().getReference("Carriers Available");
//        reference.child(userID).removeValue();
//    }
}