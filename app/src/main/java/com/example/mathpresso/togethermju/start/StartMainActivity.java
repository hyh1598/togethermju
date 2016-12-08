package com.example.mathpresso.togethermju.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.register.EmailRegisterActivity;

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
