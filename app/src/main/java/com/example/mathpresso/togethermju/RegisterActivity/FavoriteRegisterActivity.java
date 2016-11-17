package com.example.mathpresso.togethermju.RegisterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.mathpresso.togethermju.LoginActivity;
import com.example.mathpresso.togethermju.R;

import java.util.ArrayList;

public class FavoriteRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_register);

        getIntent();
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

        if ((support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true)) {
            checkList.add("봉사활동");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((study_checkbox.isChecked() == true)) {
            checkList.add("스터디");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((contest_checkbox.isChecked() == true)) {
            checkList.add("공모전");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (volunteer_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("스터디");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (contest_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("공모전");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)) {
            checkList.add("봉사활동");
            checkList.add("스터디");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (contest_checkbox.isChecked() == true)) {
            checkList.add("봉사활동");
            checkList.add("공모전");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((study_checkbox.isChecked() == true) && contest_checkbox.isChecked() == true) {
            checkList.add("스터디");
            checkList.add("공모전");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (volunteer_checkbox.isChecked() == true)
                && (study_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");
            checkList.add("스터디");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (volunteer_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");
            checkList.add("공모전");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((support_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("스터디");
            checkList.add("공모전");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true)) {
            checkList.add("봉사활동");
            checkList.add("스터디");
            checkList.add("공모전");

            startActivity(new Intent(this, LoginActivity.class));
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");
            checkList.add("스터디");
            checkList.add("공모전");

            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Toast.makeText(this, "관심사를 선택해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}
