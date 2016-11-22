package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathpresso.togethermju.RegisterActivity.EmailRegisterActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
    }

    public void clickRegisterButton(View view) {
        startActivity(new Intent(this, EmailRegisterActivity.class));
    }

    public void clickLoginButton(View view) {
        EditText editUserEmailText = (EditText) findViewById(R.id.user_email);
        EditText editUserPasswordText = (EditText) findViewById(R.id.user_password);

        if (editUserEmailText.getText().toString().equals("")) {
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if (editUserPasswordText.getText().toString().equals("")) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            String userEmail = editUserEmailText.getText().toString();
            String userPassword = editUserPasswordText.getText().toString();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            finish();
        }
    }
}
