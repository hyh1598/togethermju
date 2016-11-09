package com.example.mathpresso.togethermju;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mathpresso.togethermju.adapter.NoticeAdapter;
import com.example.mathpresso.togethermju.model.Notice;


import java.util.ArrayList;


/**
 * Created by choijinjoo on 2016. 11. 9..
 */
public class NoticeFragment extends Fragment {
    NoticeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notice, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // add mock data
        ArrayList<Notice> items = new ArrayList<>();
        items.add(new Notice(1,"공지사항입니다1","공지사항1 내용입니다."));
        items.add(new Notice(2,"공지사항입니다2","공지사항2 내용입니다."));
        items.add(new Notice(3,"공지사항입니다3","공지사항3 내용입니다."));
        items.add(new Notice(4,"공지사항입니다4","공지사항4 내용입니다."));
        items.add(new Notice(5,"공지사항입니다5","공지사항5 내용입니다."));
        items.add(new Notice(6,"공지사항입니다6","공지사항6 내용입니다."));
        items.add(new Notice(7,"공지사항입니다7","공지사항7 내용입니다."));
        items.add(new Notice(8,"공지사항입니다8","공지사항8 내용입니다."));
        items.add(new Notice(9,"공지사항입니다9","공지사항9 내용입니다."));
        items.add(new Notice(10,"공지사항입니다10","공지사항10 내용입니다."));
        items.add(new Notice(11,"공지사항입니다11","공지사항11 내용입니다."));
        items.add(new Notice(12,"공지사항입니다12","공지사항12 내용입니다."));
        items.add(new Notice(13,"공지사항입니다13","공지사항13 내용입니다."));
        items.add(new Notice(14,"공지사항입니다14","공지사항14 내용입니다."));
        items.add(new Notice(15,"공지사항입니다15","공지사항15 내용입니다."));

        mAdapter = new NoticeAdapter(recyclerView, items, getActivity(), new NoticeAdapter.OnNoticeSelectedListener() {
            @Override
            public void onSelect(Notice contest) {
                Toast.makeText(getActivity(),contest.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }
}
