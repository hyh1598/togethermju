package com.example.mathpresso.togethermju.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        EditText presentPassword = (EditText) findViewById(R.id.present_password);
        EditText newPasswrod = (EditText) findViewById(R.id.new_user_password);
        EditText checkNewPassword = (EditText) findViewById(R.id.check_new_user_password);

        if (newPasswrod.equals("")) {
            Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
        } else if (checkNewPassword.equals("")) {
            Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
        } else if (presentPassword.equals("")) {
            Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
        }
    }


}
