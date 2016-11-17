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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        mAdapter = new NoticeAdapter(recyclerView, null, getActivity(), new NoticeAdapter.OnNoticeSelectedListener() {
            @Override
            public void onSelect(Notice contest) {
                Toast.makeText(getActivity(), contest.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(mAdapter);

        loadNotices();
        return rootView;
    }

    private void loadNotices() {
        AppController.getInstance().getRestManager().getNoticeService().getNotices()
                .enqueue(new Callback<List<Notice>>() {
                    @Override
                    public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                        if (response.isSuccess()) {
                            mAdapter.clear();
                            mAdapter.add(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Notice>> call, Throwable t) {
                        Toast.makeText(getContext(), R.string.toast_message_failed_load_data, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
