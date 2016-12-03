package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mathpresso.togethermju.model.Group;

public class GroupDetailsActivity extends AppCompatActivity {
    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goup_details);
        getIntent();
        Intent intent = getIntent();
        group = (Group) intent.getSerializableExtra("group");


    }


}

