package com.example.mathpresso.togethermju.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathpresso.togethermju.start.LoginActivity;
import com.example.mathpresso.togethermju.R;

import static com.example.mathpresso.togethermju.core.AppController.user;

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
        EditText editUserNameText = (EditText) findViewById(R.id.user_name);
        EditText editUserEmailText = (EditText) findViewById(R.id.user_email);
        EditText editUserPasswordText = (EditText) findViewById(R.id.user_password);
        EditText editCheckUserPasswordText = (EditText) findViewById(R.id.check_user_password);

        if ((editUserNameText.getText().toString()).equals("")) {
            Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if ((editUserEmailText.getText().toString()).equals("")) {
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if ((editUserPasswordText.getText().toString()).equals("")) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if ((editCheckUserPasswordText.getText().toString()).equals("")) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if (editUserPasswordText.getText().toString().equals(editCheckUserPasswordText.getText().toString())) {
            //FIXME user data
            String email = editUserEmailText.getText().toString();
            String password = editUserPasswordText.getText().toString();
            String name = editUserNameText.getText().toString();

            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);

            startActivity(new Intent(this, DetailRegisterActivity.class));
        } else if ((editUserPasswordText.getText().toString()) != (editCheckUserPasswordText.getText().toString())) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
