package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.example.mathpresso.togethermju.adapter.CustomAndroidGridViewAdapter;
import com.example.mathpresso.togethermju.model.Group;

public class GroupDetailsActivity extends AppCompatActivity {
    private Group group;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;
    GridView gridView;
    CustomAndroidGridViewAdapter mAdapter;//GridViewAdapter
    public static String[] gridViewStrings = {
            "Android",
            "Java",
            "GridView",
            "ListView",
            "Adapter",
            "Custom GridView",
            "Material",
            "XML",
            "Code",

    };
    public static int[] gridViewImages = {
            R.drawable.myongji1,
            R.drawable.myongji1,
            R.drawable.myongji1,
            R.drawable.myongji1,
            R.drawable.myongji1,
            R.drawable.myongji1,
            R.drawable.myongji1,
            R.drawable.myongji1,
            R.drawable.myongji1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goup_details);
        getIntent();
        Intent intent = getIntent();
        group = (Group) intent.getSerializableExtra("group");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = (GridView) findViewById(R.id.grid);
        mAdapter = new CustomAndroidGridViewAdapter(this, gridViewStrings, gridViewImages, new CustomAndroidGridViewAdapter.OnUserSelectedListener() {
            @Override
            public void onSelect(String s) {
                Toast.makeText(getApplicationContext(),"s 를 선택",Toast.LENGTH_LONG);
                Log.d("ItemClick","CLICK");
            }
        });
        gridView.setAdapter(mAdapter);
        //Toolbar 초기화
        initInstances();
    }
    private void initInstances() {
        rootLayoutAndroid = (CoordinatorLayout) findViewById(R.id.android_coordinator_layout);
        collapsingToolbarLayoutAndroid = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_android_layout);
        //Group Name Binding
        collapsingToolbarLayoutAndroid.setTitle(group.getName());
    }
    private void uploadGroupMember(){


    }



}

