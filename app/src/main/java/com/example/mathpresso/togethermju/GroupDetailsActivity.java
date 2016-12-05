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
import com.example.mathpresso.togethermju.adapter.HorizontalListViewAdapter;
import com.example.mathpresso.togethermju.listview.HorizontalListView;
import com.example.mathpresso.togethermju.model.Group;
import com.example.mathpresso.togethermju.model.User;

import java.util.ArrayList;

public class GroupDetailsActivity extends AppCompatActivity {
    private Group group;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;
    GridView gridView;
    CustomAndroidGridViewAdapter gridViewAdapter;//GridViewAdapter
    HorizontalListViewAdapter horizontalListViewAdapter;

    //test data
    public ArrayList<User> userlist = new ArrayList<User>();
    public ArrayList<User> Recommand_userlist = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        Intent intent = getIntent();
        group = (Group) intent.getSerializableExtra("group");

        //Toolbar 초기화
        initToolbar();


        Recommand_userlist.add(new User("손지호","hardho@naver.com","컴퓨터공학과"));
        Recommand_userlist.add(new User("최진주","hardho@naver.com","컴퓨터공학과"));
        Recommand_userlist.add(new User("성목경","hardho@naver.com","컴퓨터공학과"));
        Recommand_userlist.add(new User("이동혁","hardho@naver.com","컴퓨터공학과"));
        Recommand_userlist.add(new User("범위테스트","hardho@naver.com","컴퓨터공학과"));
        Recommand_userlist.add(new User("범위테스트","hardho@naver.com","컴퓨터공학과"));
        Recommand_userlist.add(new User("범위테스트","hardho@naver.com","컴퓨터공학과"));
        Recommand_userlist.add(new User("범위테스트","hardho@naver.com","컴퓨터공학과"));
        Recommand_userlist.add(new User("범위테스트","hardho@naver.com","컴퓨터공학과"));
        //test data insert
        userlist.add(new User("TEST1","TEST@mju","TEST"));
        userlist.add(new User("TEST2","TEST@mju","TEST"));
        userlist.add(new User("TEST3","TEST@mju","TEST"));
        userlist.add(new User("TEST4","TEST@mju","TEST"));
        userlist.add(new User("TEST5","TEST@mju","TEST"));

        //Horizontal List View Binding
        HorizontalListView recommand_listview = (HorizontalListView) findViewById(R.id.RecommandListView);
        horizontalListViewAdapter = new HorizontalListViewAdapter(getApplicationContext(), Recommand_userlist, new HorizontalListViewAdapter.OnRecommandUserSelectedListener() {
            @Override
            public void onSelect(User user) {
                Toast.makeText(getApplicationContext(),user.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        recommand_listview.setAdapter(horizontalListViewAdapter);

        gridView = (GridView) findViewById(R.id.grid);
        gridViewAdapter = new CustomAndroidGridViewAdapter(this, userlist, new CustomAndroidGridViewAdapter.OnUserSelectedListener() {
            @Override
            public void onSelect(User user) {
                Toast.makeText(getApplicationContext(),user.getName()+"를 선택",Toast.LENGTH_SHORT).show();
                Log.d("ItemClick","CLICK");
            }
        });
        gridView.setAdapter(gridViewAdapter);

        //uploadGroupMember();
        //Loading...
    }
    private void initToolbar() {
        //Toolbal Set for showing Group Name
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rootLayoutAndroid = (CoordinatorLayout) findViewById(R.id.android_coordinator_layout);
        collapsingToolbarLayoutAndroid = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_android_layout);
        //Group Name Binding
        collapsingToolbarLayoutAndroid.setTitle(group.getName());
    }

    //Group MemberUploading
    private void uploadGroupMember() {
    }

    //Recommand User Uploading
    private void  uploadRecommandUser(){

    }

}

