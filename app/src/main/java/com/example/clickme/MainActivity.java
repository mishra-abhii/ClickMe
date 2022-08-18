package com.example.clickme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.clickme.auth.SignInActivity;

public class MainActivity extends AppCompatActivity {

    Boolean counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //========================================================================================
    // Below code is related to phone authentication part

    @Override
    protected void onStart() {
        super.onStart();
        checkUserStatus();
    }

    private void checkUserStatus() {
        SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
        counter = sharedPreferences.getBoolean("logincounter",Boolean.valueOf(String.valueOf(MODE_PRIVATE)));
        if(!counter){
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        } else {
            checkUserInfoStatus();
        }
    }

    private void checkUserInfoStatus() {
        SharedPreferences getUserInfoStatus = getSharedPreferences("userInfo", MODE_PRIVATE);
        boolean userInfoSaved = getUserInfoStatus.getBoolean("writtenToDB", false);
        if (!userInfoSaved) {
            startActivity(new Intent(this, UserInfoActivity.class));
            finish();
        }
    }

}