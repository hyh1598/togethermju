package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        Button btnGotoMainActivity = (Button)findViewById(R.id.btnMain);
        btnGotoMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TempActivity.this,MainActivity.class));
            }
        });
    }

    public void clickDonghyukButton(View view) {
        startActivity(new Intent(this, StartMainActivity.class));
    }
}
