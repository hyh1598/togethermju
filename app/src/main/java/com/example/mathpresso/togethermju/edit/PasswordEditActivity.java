package com.example.mathpresso.togethermju.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mathpresso.togethermju.MainActivity;
import com.example.mathpresso.togethermju.R;

public class PasswordEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_edit);
    }

    public void clickCancelButton(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void clickEditButton(View view) {
        // editUser();
    }


}
