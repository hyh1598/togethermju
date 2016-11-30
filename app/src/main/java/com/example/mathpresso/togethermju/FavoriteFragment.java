package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mathpresso.togethermju.adapter.WatchedNoticeAdapter;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.Notice;
import com.example.mathpresso.togethermju.model.NoticeHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by choijinjoo on 2016. 11. 9..
 */
public class FavoriteFragment extends Fragment {

    WatchedNoticeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mAdapter = new WatchedNoticeAdapter(recyclerView, null, getActivity(), new WatchedNoticeAdapter.OnNoticeSelectedListener() {
            @Override
            public void onSelect(Notice notice) {
                Intent intent = new Intent(getContext(),NoticeDetailsActivity.class);
                intent.putExtra("notice",notice);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);

        loadWatchedNotice();
        return rootView;
    }

    private void loadWatchedNotice() {
        // test data "hardho@naver.com"
        String email = AppController.getInstance().getStringValue("email", "hardho@naver.com");
        AppController.getInstance().getRestManager().getNoticeService().getWatchednotices("hardho@naver.com")
                .enqueue(new Callback<List<Notice>>() {
                    @Override
                    public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                        if (response.isSuccess()) {
                            mAdapter.clear();
                            mAdapter.add(response.body());
                            // watch한 notice는 db에 저장.
                            NoticeHelper.getInstance().setNoticeList(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Notice>> call, Throwable t) {
                        // load를 실패하면 db에 저장된 watched notice를 띄워줌.
                        List<Notice> noticeList = NoticeHelper.getInstance().getNoticeList();
                        mAdapter.clear();
                        mAdapter.add(noticeList);
                    }
                });
    }

}
