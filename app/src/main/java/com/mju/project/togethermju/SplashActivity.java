package com.mju.project.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.mju.project.togethermju.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Intent intent = new Intent(SplashActivity.this, TempActivity.class);
                startActivity(intent);

                finish();

            }
        };

        handler.sendEmptyMessageDelayed(0, 2000);
    }
}
