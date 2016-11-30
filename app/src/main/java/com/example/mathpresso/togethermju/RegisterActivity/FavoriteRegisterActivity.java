package com.example.mathpresso.togethermju.RegisterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.mathpresso.togethermju.LoginActivity;
import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavoriteRegisterActivity extends AppCompatActivity {
    //FIXME user
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_register);

        getIntent();
    }

    public void postUser() {
        Call<User> call = AppController.getInstance().getRestManager().getNetworkService().post_user(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccess()) {
                    Toast.makeText(getBaseContext(), "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    int statusCode = response.code();
                    Log.i("MY TAG", "응답 코드: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("MY TAG", "서버 onFailure 내용: " + t.getMessage());

            }
        });
    }

    public void clickBackButton(View view) {
        startActivity(new Intent(this, DetailRegisterActivity.class));
    }

    public void clickRegisterButton(View view) {
        CheckBox support_checkbox = (CheckBox) findViewById(R.id.support_checkbox);
        CheckBox volunteer_checkbox = (CheckBox) findViewById(R.id.volunteer_checkbox);
        CheckBox study_checkbox = (CheckBox) findViewById(R.id.study_checkbox);
        CheckBox contest_checkbox = (CheckBox) findViewById(R.id.contest_checkbox);

        ArrayList<String> checkList = new ArrayList<String>();

        if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == false)) {
            checkList.add("봉사활동");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == false)) {
            checkList.add("스터디");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == false)) {
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("스터디");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == false)) {
            checkList.add("봉사활동");
            checkList.add("스터디");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == false)) {
            checkList.add("봉사활동");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == false)) {
            checkList.add("스터디");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");
            checkList.add("스터디");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("스터디");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == false)) {
            checkList.add("봉사활동");
            checkList.add("스터디");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");
            checkList.add("스터디");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Toast.makeText(this, "관심사를 선택해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}