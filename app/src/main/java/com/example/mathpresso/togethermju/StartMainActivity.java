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
    private GoogleApiClient mGoogleApiClient;
    GoogleCloudMessaging gcm;
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
        new RegisterTask().execute(null, null, null);
        startActivity(new Intent(this, EmailRegisterActivity.class));
    }

    class RegisterTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(Void... params) {
            String string;

            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                }

                user.setRid(gcm.register(User.PROJECT_NUMBER));
                Log.i("haha", "ID: " + user.getRid());
                string = "DEVICE REGISTERED, REGISTER ID IS\n" + user.getRid();
            } catch (IOException e) {
                string = "ERROR" + e.getMessage();
            }
            return string;
        }

        @Override
        protected void onPostExecute(String msg) {}
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
    }
    @Override
    protected void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }
}
