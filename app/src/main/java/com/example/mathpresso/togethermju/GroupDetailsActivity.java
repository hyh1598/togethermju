package com.example.mathpresso.togethermju;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathpresso.togethermju.adapter.CustomAndroidGridViewAdapter;
import com.example.mathpresso.togethermju.adapter.HorizontalListViewAdapter;
import com.example.mathpresso.togethermju.adapter.ReplyListViewAdapter;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.listview.HorizontalListView;
import com.example.mathpresso.togethermju.model.DefaultResponse;
import com.example.mathpresso.togethermju.model.Group;
import com.example.mathpresso.togethermju.model.GroupReply;
import com.example.mathpresso.togethermju.model.Notice;
import com.example.mathpresso.togethermju.model.User;
import com.example.mathpresso.togethermju.tool.Utils;

import java.util.ArrayList;
import java.util.List;

import de.mrapp.android.dialog.MaterialDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GroupDetailsActivity extends AppCompatActivity implements View.OnClickListener {

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

    EditText etxtContent;
    RelativeLayout btnSubmit;
    LinearLayout containerComment;
    RelativeLayout btnJoin;
    TextView attendstate;


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
        // layout 초기화
        initializeLayout();
        isGroupMember();
        loadGroupMember();
        loadreplies();


    }

    private void initializeLayout() {

        //Horizontal List View Binding
        HorizontalListView member_listview = (HorizontalListView) findViewById(R.id.MemberListView);
        horizontalListViewAdapter = new HorizontalListViewAdapter(getApplicationContext(), userlist, new HorizontalListViewAdapter.OnMemberSelectedListener() {
            @Override
            public void onSelect(User user) {
                Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);

                intent.putExtra("user", user);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), user.getName(), Toast.LENGTH_SHORT).show();

            }
        });

        member_listview.setAdapter(horizontalListViewAdapter);


        replyListViewAdapter = new ReplyListViewAdapter(null, this, new ReplyListViewAdapter.OnReplySelectedListener() {
            @Override
            public void onSelect(Notice notice) {
                Toast.makeText(GroupDetailsActivity.this, "reply click", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(replyListViewAdapter);
        etxtContent = (EditText) findViewById(R.id.etxtContent);
        btnSubmit = (RelativeLayout) findViewById(R.id.btnSubmit);
        containerComment = (LinearLayout) findViewById(R.id.containerComment);
        btnSubmit.setOnClickListener(this);
        btnJoin = (RelativeLayout) findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(this);
        attendstate = (TextView)findViewById(R.id.attend_status);


    }


    private void initToolbar() {
        //Toolbal Set for showing Group Name
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rootLayoutAndroid = (CoordinatorLayout) findViewById(R.id.android_coordinator_layout);
        collapsingToolbarLayoutAndroid = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_android_layout);
        //Group Name Binding
        collapsingToolbarLayoutAndroid.setTitle(group.getName());
        TextView textView = (TextView)findViewById(R.id.introduce_txtv);
        textView.setText(group.getIntroduce());
    }


    private void loadreplies() {
        AppController.getInstance().getRestManager().getGroupService().getReplylist(group.getId()).enqueue(new Callback<List<GroupReply>>() {
            @Override
            public void onResponse(Call<List<GroupReply>> call, Response<List<GroupReply>> response) {
                if (response.isSuccess()) {
                    replyListViewAdapter.clear();
                    replyListViewAdapter.add(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<GroupReply>> call, Throwable t) {


            }
        });
    }
    // load group member
    private void loadGroupMember() {

        AppController.getInstance().getRestManager().getGroupService().getGroupMember(group.getId())
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccess()) {
                            Log.d("uploadGroupMember", "SUCCESS!!");

                            horizontalListViewAdapter.clear();
                            horizontalListViewAdapter.add(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.d("uploadGroupMember", "Server Problem");
                    }
                });
    }

    //upload group member reply
    private void uploadGroupReply(String content) {
        String email = AppController.getInstance().getStringValue("email", "");
        AppController.getInstance().getRestManager().getGroupService().uploadGroupReply(email, group.getId(), content)
                .enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if (response.isSuccess() && response.body().getResult().equals("success")) {
                            //refresh
                            loadreplies();
                            etxtContent.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
    }

    private void isGroupMember() {
        String email = AppController.getInstance().getStringValue("email", "");
        AppController.getInstance().getRestManager().getGroupService().isGroupMember(email, group.getId())
                .enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if (response.isSuccess() && response.body().getResult().equals("true")) {
                            if (response.body().getResult().equals("true")) {
                                Log.d("INPUTSTATUE","VISIBLE");
                                containerComment.setVisibility(View.VISIBLE);
                                attendstate.setText("참여 중");
                            } else {
                                containerComment.setVisibility(View.GONE);

                                Log.d("INPUTSTATUE","GONE");
                                attendstate.setText("그룹 참여하기");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
    }

    private void attendGroup() {
        String email = AppController.getInstance().getStringValue("email", "");
        AppController.getInstance().getRestManager().getGroupService().attendGroup(email, group.getId())
                .enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if (response.isSuccess() && response.body().getResult().equals("success")) {
                            isGroupMember();
                            loadGroupMember();
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                String content = etxtContent.getText().toString();
                if (!Utils.isEmpty(content)) {
                    uploadGroupReply(content);
                }
                break;
            case R.id.btnJoin:
                // attend group alert dialog
                MaterialDialog dialog = new MaterialDialog.Builder(this)
                        .setTitle("그룹에 가입하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                attendGroup();
                                dialog.dismiss();
                                isGroupMember();
                            }
                        })
                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
                break;
        }
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etxtContent.getWindowToken(), 0);
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

