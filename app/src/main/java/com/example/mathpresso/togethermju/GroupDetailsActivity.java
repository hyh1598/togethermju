package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.example.mathpresso.togethermju.adapter.CustomAndroidGridViewAdapter;
import com.example.mathpresso.togethermju.adapter.HorizontalListViewAdapter;
import com.example.mathpresso.togethermju.adapter.ReplyListViewAdapter;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.listview.HorizontalListView;
import com.example.mathpresso.togethermju.model.Group;
import com.example.mathpresso.togethermju.model.Notice;
import com.example.mathpresso.togethermju.model.Reply;
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
    HorizontalListViewAdapter horizontalListViewAdapter;
    ReplyListViewAdapter replyListViewAdapter;
    RecyclerView recyclerView;
    //Group member Activity 로 옮길 예정 grid view
    GridView gridView;
    CustomAndroidGridViewAdapter gridViewAdapter;//GridViewAdapter



    //test data
    public ArrayList<User> userlist = new ArrayList<User>();
    public ArrayList<Reply> replylist = new ArrayList<Reply>();
    public ArrayList<User> Recommand_userlist = new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        Intent intent = getIntent();
        group = (Group) intent.getSerializableExtra("group");

        //Toolbar 초기화
        initToolbar();

        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글1"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글2"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));
        replylist.add(new Reply("손지호","2016-12-6","테스트 댓글3"));





        //Horizontal List View Binding
        HorizontalListView member_listview = (HorizontalListView) findViewById(R.id.MemberListView);
        horizontalListViewAdapter = new HorizontalListViewAdapter(getApplicationContext(), userlist, new HorizontalListViewAdapter.OnMemberSelectedListener() {
            @Override
            public void onSelect(User user) {
                Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),user.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        member_listview.setAdapter(horizontalListViewAdapter);

        replyListViewAdapter = new ReplyListViewAdapter(replylist, this, new ReplyListViewAdapter.OnReplySelectedListener() {
            @Override
            public void onSelect(Notice notice) {
                Toast.makeText(GroupDetailsActivity.this,"reply click",Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(replyListViewAdapter);

        uploadGroupMember();
        //loadreplys();

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
    private void loadreplys(){
        AppController.getInstance().getRestManager().getGroupService().getReplylist(group.getId()).enqueue(new Callback<List<Reply>>() {
            @Override
            public void onResponse(Call<List<Reply>> call, Response<List<Reply>> response) {
                if(response.isSuccess()){

                }
            }

            @Override
            public void onFailure(Call<List<Reply>> call, Throwable t) {

            }
        });
    }
    //Group MemberUploading
    private void uploadGroupMember() {
        AppController.getInstance().getRestManager().getGroupService().getGroupMember(group.getId())
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccess()) {
                            Log.d("uploadGroupMember","SUCCESS!!");
                            horizontalListViewAdapter.clear();
                            horizontalListViewAdapter.add(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.d("uploadGroupMember","Server Problem");
                    }
                });
    }

    //Recommand User Uploading
//    private void  uploadRecommandUser(){
//        AppController.getInstance().getRestManager().getGroupService().getRecommandMember(group.getNotice())
//                .enqueue(new Callback<List<User>>() {
//                    @Override
//                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                        if (response.isSuccess()) {
//                            Log.d("uploadRecommandsMember","SUCCESS!!");
//                            horizontalListViewAdapter.clear();
//                            horizontalListViewAdapter.add(response.body());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<User>> call, Throwable t) {
//                        Log.d("uploadRecommandsMember","Server Problem");
//                    }
//                });
//    }

}

