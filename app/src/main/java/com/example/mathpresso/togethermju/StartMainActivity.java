package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.mathpresso.togethermju.RegisterActivity.EmailRegisterActivity;
import com.example.mathpresso.togethermju.model.User;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;

import static com.example.mathpresso.togethermju.core.AppController.user;

public class StartMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_main);

        getIntent();
    }

    public void clickLoginButton(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void clickRegisterButton(View view) {
        startActivity(new Intent(this, EmailRegisterActivity.class));
    }
}
