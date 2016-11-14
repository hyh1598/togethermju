package com.example.mathpresso.togethermju.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathpresso.togethermju.LoginActivity;
import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.Register.DetailRegisterActivity;

public class EmailRegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_register);

        getIntent();
    }

    public void clickBackButton(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void clickNextButton(View view) {
        EditText editUserEmailText = (EditText) findViewById(R.id.user_email);
        EditText editUserPasswordText = (EditText) findViewById(R.id.user_password);
        EditText editCheckUserPasswordText = (EditText) findViewById(R.id.check_user_password);

        if ((editUserEmailText.getText().toString()).equals("")) {
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if ((editUserPasswordText.getText().toString()).equals("")) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if ((editCheckUserPasswordText.getText().toString()).equals("")) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if (editUserPasswordText.getText().toString().equals(editCheckUserPasswordText.getText().toString())) {
            startActivity(new Intent(this, DetailRegisterActivity.class));
        } else if ((editUserPasswordText.getText().toString()) != (editCheckUserPasswordText.getText().toString())) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        } else {
            String userEmail = editUserEmailText.getText().toString();
            String userPassword = editUserPasswordText.getText().toString();
            startActivity(new Intent(this, DetailRegisterActivity.class));
        }
    }
}
