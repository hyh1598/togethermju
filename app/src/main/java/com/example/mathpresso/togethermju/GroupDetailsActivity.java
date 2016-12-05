package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.mathpresso.togethermju.adapter.CustomAndroidGridViewAdapter;
import com.example.mathpresso.togethermju.adapter.HorizontalListViewAdapter;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.listview.HorizontalListView;
import com.example.mathpresso.togethermju.model.Group;
import com.example.mathpresso.togethermju.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Button attend = (Button)findViewById(R.id.attend_btn);
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Toolbar 초기화
        initToolbar();

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

        uploadGroupMember();
        uploadRecommandUser();
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
        AppController.getInstance().getRestManager().getGroupService().getGroupMember(group.getId())
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccess()) {
                            Log.d("uploadGroupMember","SUCCESS!!");
                            gridViewAdapter.clear();
                            gridViewAdapter.add(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.d("uploadGroupMember","Server Problem");
                    }
                });
    }

    //Recommand User Uploading
    private void  uploadRecommandUser(){
        AppController.getInstance().getRestManager().getGroupService().getRecommandMember(group.getNotice())
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccess()) {
                            Log.d("uploadRecommandsMember","SUCCESS!!");
                            horizontalListViewAdapter.clear();
                            horizontalListViewAdapter.add(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.d("uploadRecommandsMember","Server Problem");
                    }
                });
    }

}

