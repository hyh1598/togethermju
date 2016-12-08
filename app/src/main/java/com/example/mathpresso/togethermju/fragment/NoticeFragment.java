package com.example.mathpresso.togethermju.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.widget.Toast;

import com.example.mathpresso.togethermju.adapter.NoticeAdapter;
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
public class NoticeFragment extends Fragment implements View.OnClickListener{
    NoticeAdapter mAdapter;
    ImageView imgvFilter;
    @Nullable
    /*
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notice, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        //imgvFilter = (ImageView)rootView.findViewById(R.id.imgvFilter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mAdapter = new NoticeAdapter(recyclerView, null, getActivity(), new NoticeAdapter.OnNoticeSelectedListener() {
            @Override
            public void onSelect(Notice notice) {
                Toast.makeText(getActivity(), notice.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), NoticeDetailsActivity.class);

                intent.putExtra("notice",notice);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);

        imgvFilter.setOnClickListener(this);

        loadNotices();
        return rootView;

    }
*/

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

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
          //  case R.id.imgvFilter:
              //  break;
        }
    }

}
