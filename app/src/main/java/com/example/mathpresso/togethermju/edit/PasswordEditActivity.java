package com.example.mathpresso.togethermju.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathpresso.togethermju.MainActivity;
import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mathpresso.togethermju.core.AppController.user;

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

    public void editPassword() {
        Call<User> call = AppController.getInstance().getRestManager().getUserService().editUserPassword(user.getPassword());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccess()) {

                    Log.d("jsondata",String.valueOf(response.message()));
                    Toast.makeText(getBaseContext(), "비밀번호 변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    int statusCode = response.code();
                    Log.d("jsondata",String.valueOf(response.body()));
                    Log.i("MY TAG", "응답 코드: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("MY TAG", "서버 onFailure 내용: " + t.getMessage());
            }
        });
    }

}
