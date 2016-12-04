package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.mathpresso.togethermju.core.AppController;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String email = AppController.getInstance().getStringValue("email", "");

//                Checking Login Startte
                if (email.length() > 0) {
                    //if device have Login info  ---> Start MainActivity(for Notices view)
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //if device don`t have Login info ---> Start [Login]
                    Intent intent = new Intent(SplashActivity.this, StartMainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

        }, 1000);
    }
}
