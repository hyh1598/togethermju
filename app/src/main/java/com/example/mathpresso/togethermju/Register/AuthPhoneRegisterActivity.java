package com.example.mathpresso.togethermju.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathpresso.togethermju.R;

public class AuthPhoneRegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_phone_register);

        getIntent();
    }

    public void clickAuthButton(View view) {
        EditText editAuthNumberEdit = (EditText) findViewById(R.id.auth_number);

        if ((editAuthNumberEdit.getText().toString()).equals("")) {
            Toast.makeText(this, "인증번호를 입력하세요.", Toast.LENGTH_SHORT).show();
        } else if (editAuthNumberEdit.getText().toString().length() == 4) {
            Toast.makeText(this, "인증되었습니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "인증번호를 정확히 입력하세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickBackButton(View view) {
        startActivity(new Intent(this, PhoneRegisterActivity.class));
    }

    public void clickNextButton(View view) {
        EditText editAuthNumberEdit = (EditText) findViewById(R.id.auth_number);

        if ((editAuthNumberEdit.getText().toString()).equals("")) {
            Toast.makeText(this, "인증번호를 입력하세요.", Toast.LENGTH_SHORT).show();
        } else {
            int authNumber = Integer.parseInt(editAuthNumberEdit.getText().toString());
            startActivity(new Intent(this, EmailRegisterActivity.class));
        }
    }
}
