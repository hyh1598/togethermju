package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mathpresso.togethermju.RegisterActivity.GroupRegisterActivity;
import com.example.mathpresso.togethermju.adapter.GroupAdapter;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.DefaultResponse;
import com.example.mathpresso.togethermju.model.Group;
import com.example.mathpresso.togethermju.model.Notice;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeDetailsActivity extends AppCompatActivity {

    private TextView txtvTitle;
    private TextView textViewContent;
    private LinearLayout btnWatch;
    private Notice notice;
    private TextView txtvWatch;

    GroupAdapter mAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onStart() {
        super.onStart();
        loadNoticeGroupList(notice.getNoticeSeq());
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);
        getIntent();

        //GET Selected Notice info
        Intent intent = getIntent();
        notice = (Notice) intent.getSerializableExtra("notice");
        String title = notice.getTitle();
        String content = notice.getContent();

        txtvTitle = (TextView) findViewById(R.id.txtvTitle);
        textViewContent = (TextView) findViewById(R.id.notice_detail_content);
        btnWatch = (LinearLayout) this.findViewById(R.id.btnWatch);
        txtvWatch = (TextView) findViewById(R.id.txtvWatch);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //Watch Button Setting Listener
        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnWatch.isSelected()) {
                    postWatchNotice(notice);
                    //서버로 watch +1
                } else {
                    postWatchNotice(notice);
                    //서버로 watch값 -1
                }
            }
        });

        txtvTitle.setText(title);
        textViewContent.setText(content.trim());
        //Watch State Button initialization
        AppController.getInstance().getRestManager().getNoticeService().checkWatch(AppController.user.getEmail(), notice.getNoticeSeq())
                .enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if (response.isSuccess()) {
                            if (response.body().getResult().equals("watch")) {
                                initWatchBtn(true);
                            } else {
                                initWatchBtn(false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        Log.d("watch", t.toString());
                    }
                });
        //Group List Adapter.
        mAdapter = new GroupAdapter(null, this, new GroupAdapter.OnGroupSelectedListener() {
            @Override
            public void onSelect(Group group) {
                Intent intent = new Intent(NoticeDetailsActivity.this,GroupDetailsActivity.class);
                intent.putExtra("group",group);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        //Group Add Button.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Group 등록 화면 전환
                Intent intent = new Intent(getApplicationContext(), GroupRegisterActivity.class);
                intent.putExtra("notice_seq", notice.getNoticeSeq());
                startActivity(intent);

            }
        });

    }


    private void loadNoticeGroupList(String id) {
        //FIXME id값으로 바꿔야함
        AppController.getInstance().getRestManager().getGroupService().getNoticeGroup(notice.getNoticeSeq())
                .enqueue(new Callback<List<Group>>() {
                    @Override
                    public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
                        if (response.isSuccess()) {
                            mAdapter.clear();
                            mAdapter.add(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Group>> call, Throwable t) {

                    }
                });
    }

    private void postWatchNotice(Notice notice) {
        AppController.getInstance().getRestManager().getNoticeService().watchNotice(AppController.user.getEmail(), notice.getNoticeSeq())
                .enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if (response.isSuccess()) {
                            if (response.body().getResult().equals("watch")) {
                                initWatchBtn(true);
                            } else {
                                initWatchBtn(false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
    }

    private void initWatchBtn(boolean watch) {
        if (watch) {
            txtvWatch.setText("UNWATCH");

        } else {
            txtvWatch.setText("WATCH");

        }
    }

    //LINK 해당 게시물로
    public void btnHomePage(View view) {
        String board = notice.getBoard();
        String seq = notice.getNoticeSeq();
        //http://www.mju.ac.kr/mbs/mjukr/jsp/board/view.jsp?spage=1&boardId=11294&boardSeq=60873592
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.mju.ac.kr/mbs/mjukr/jsp/board/view.jsp?boardId=" + board + "&boardSeq=" + seq)));
    }
}
