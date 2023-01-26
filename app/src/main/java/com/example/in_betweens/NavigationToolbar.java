package com.example.in_betweens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.os.Bundle;

public class NavigationToolbar extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrier_map);
//        drawerLayout=findViewById(R.id.drawer);


    }
    public  void ClickMenu(View view){
        openDrawer(drawerLayout);
    }
    public static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer(GravityCompat.START);

    }
    public void Clicklogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);



        }

    }

    public void Clickhome(View view){
        recreate();

    }

//    public  void clickDashboard(View view){
//        redirectActivity(this,DashBoard.class);
//
//
//
//    }
//    public  void clickAboutUs(View view){
//        redirectActivity(this,AboutUs.class);
//
//
//    }
}