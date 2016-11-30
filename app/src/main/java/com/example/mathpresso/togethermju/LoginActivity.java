package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathpresso.togethermju.RegisterActivity.EmailRegisterActivity;
import com.example.mathpresso.togethermju.model.User;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.example.mathpresso.togethermju.core.AppController;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mathpresso.togethermju.core.AppController.user;


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

            userAuth(userEmail, userPassword);
        }
    }

    public void userAuth(String email, String password) {
        Call<User> call = AppController.getInstance().getRestManager().getUserService().getUserAuth(email, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccess()) {
                    AppController.user = response.body();
                    if (response.message().equals("OK")) {
                        Toast.makeText(getBaseContext(), "로그인되었습니다", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(getBaseContext(), "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    int statusCode = response.code();
                    Log.i("MY TAG", "응답 코드: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(), "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
