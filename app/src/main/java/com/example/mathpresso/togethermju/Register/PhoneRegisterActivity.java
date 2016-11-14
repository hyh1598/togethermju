package com.example.mathpresso.togethermju.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.Register.AuthPhoneRegisterActivity;
import com.example.mathpresso.togethermju.StartMainActivity;

public class PhoneRegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register);

        getIntent();

        Spinner phoneSpinner = (Spinner) findViewById(R.id.phone_spinner);

        ArrayAdapter phoneAdapter = ArrayAdapter.createFromResource(this, R.array.phone,
                android.R.layout.simple_spinner_item);
        phoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        phoneSpinner.setAdapter(phoneAdapter);
    }

    public void clickCancelButton(View view) {
        startActivity(new Intent(this, StartMainActivity.class));
    }

    public void clickSendButton(View view) {
        EditText editUserPhoneEdit = (EditText) findViewById(R.id.user_phone);

        if ((editUserPhoneEdit.getText().toString()).equals("")) {
            Toast.makeText(this, "전화번호를 입력하세요.", Toast.LENGTH_SHORT).show();
        } else if (editUserPhoneEdit.getText().toString().length() == 10) {
            Toast.makeText(this, "인증번호를 전송했습니다.", Toast.LENGTH_SHORT).show();
            int userPhone = Integer.parseInt(editUserPhoneEdit.getText().toString());
            startActivity(new Intent(this, AuthPhoneRegisterActivity.class));
        } else if (editUserPhoneEdit.getText().toString().length() == 11) {
            Toast.makeText(this, "인증번호를 전송했습니다.", Toast.LENGTH_SHORT).show();
            int userPhone = Integer.parseInt(editUserPhoneEdit.getText().toString());
            startActivity(new Intent(this, AuthPhoneRegisterActivity.class));
        } else {
            Toast.makeText(this, "전화번호를 올바르게 입력하세요.", Toast.LENGTH_SHORT).show();
        }
    }
}
