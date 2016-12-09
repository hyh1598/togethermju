package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathpresso.togethermju.Network.urlToImageProcessor;
import com.example.mathpresso.togethermju.model.User;

public class UserInfoActivity extends AppCompatActivity {
    private User user;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;
    RecyclerView recyclerView;
    ImageView imageView ;
    TextView name;
    TextView email;
    TextView major;
    TextView gender;
    TextView age;

    @Override
    protected void onStart() {
        super.onStart();
        if(user.getEmail()!=null)
            new MainImageLoadProcessor().execute(user.getEmail());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        //Toolbar 초기화
        initToolbar();
        String temp;
        int cal;
        imageView = (ImageView)findViewById(R.id.imageView01);
        name = (TextView) findViewById(R.id.name);
        major = (TextView) findViewById(R.id.major);
        age = (TextView) findViewById(R.id.age);
        email = (TextView) findViewById(R.id.email);
        gender = (TextView) findViewById(R.id.gender);


        name.setText(user.getName());
        major.setText(user.getMajor());

        temp = user.getBirth().substring(0, 4);
        cal = Integer.parseInt(temp);
        cal = 2017 - cal;
        String.valueOf(cal);
        temp = cal + (" (" + user.getBirth() + ")");

        age.setText(temp);
        email.setText(user.getEmail());
        gender.setText(user.getGender());

    }
    private void initToolbar() {
        //Toolbal Set for showing Group Name
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rootLayoutAndroid = (CoordinatorLayout) findViewById(R.id.android_coordinator_layout);
        collapsingToolbarLayoutAndroid = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_android_layout);
        //Group Name Binding
        if(user!=null)collapsingToolbarLayoutAndroid.setTitle(user.getName());
    }

    private class MainImageLoadProcessor extends urlToImageProcessor {
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (bitmap != null) {
                //upload image on AppController user instance
                Log.d("IMAGESTATUS", "SUCCESS");
                imageView.setImageBitmap(bitmap);
            }
        }
    }




}

