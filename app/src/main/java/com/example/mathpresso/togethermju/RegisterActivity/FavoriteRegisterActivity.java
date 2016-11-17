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


public class FavoriteRegisterActivity extends AppCompatActivity implements View.OnClickListener{
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

// register를 누를때마다 check?-> add되기때문에 데이터가 버튼을 누를때마다 들어갈거같아요. 그 처리도 필요할거같아요!
// 버튼 눌렀을때 user에 addFavoritelist하면 좋을거같아요!
//    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.support_checkbox:
//                // if checked -> add 서포터즈
//                // if unchecked -> list에서 delete
//                break;
//            case R.id.volunteer_checkbox:
//                break;
//            case R.id.study_checkbox:
//                break;
//            case R.id.contest_checkbox:
//                break;
//
        }
//    }

    public void clickRegisterButton(View view) {
        CheckBox support_checkbox = (CheckBox) findViewById(R.id.support_checkbox);
        CheckBox volunteer_checkbox = (CheckBox) findViewById(R.id.volunteer_checkbox);
        CheckBox study_checkbox = (CheckBox) findViewById(R.id.study_checkbox);
        CheckBox contest_checkbox = (CheckBox) findViewById(R.id.contest_checkbox);

        ArrayList<String> checkList = new ArrayList<String>();

        if ((support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true)) {
            checkList.add("봉사활동");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((study_checkbox.isChecked() == true)) {
            checkList.add("스터디");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((contest_checkbox.isChecked() == true)) {
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (volunteer_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("스터디");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (contest_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)) {
            checkList.add("봉사활동");
            checkList.add("스터디");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (contest_checkbox.isChecked() == true)) {
            checkList.add("봉사활동");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((study_checkbox.isChecked() == true) && contest_checkbox.isChecked() == true) {
            checkList.add("스터디");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (volunteer_checkbox.isChecked() == true)
                && (study_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");
            checkList.add("스터디");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (volunteer_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("스터디");
            checkList.add("공모전");

            user.setUserFavorite(checkList);
            postUser();
            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true)) {
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
