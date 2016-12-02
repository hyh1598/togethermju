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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathpresso.togethermju.adapter.GroupAdapter;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.Group;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by choijinjoo on 2016. 11. 9..
 */

public class GroupFragment extends Fragment implements View.OnClickListener{
    GroupAdapter mAdapter;
    ImageView imgvFilter;
    TextView txtvType;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        imgvFilter = (ImageView) rootView.findViewById(R.id.imgvFilter);
        txtvType = (TextView) rootView.findViewById(R.id.txtvType);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        //add mock data


        mAdapter = new GroupAdapter(recyclerView, null, getActivity(), new GroupAdapter.OnGroupSelectedListener() {
            @Override
            public void onSelect(Group group) {

                Intent intent = new Intent(getActivity(), GroupDetailsActivity.class);
                intent.putExtra("group", group);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
        imgvFilter.setOnClickListener(this);
        // default : 내그룹
        loadJoinGroupList(GroupFilterDialog.FILTER_MINE);
        return rootView;
    }

    private void loadJoinGroupList(String email) {
        //FIXME id값으로 바꿔야함
        AppController.getInstance().getRestManager().getGroupService().getJoinGroup(email)
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgvFilter:
                showGroupFilterDialog();
                break;
        }
    }

    private void showGroupFilterDialog() {
        GroupFilterDialog.init(getActivity(), new GroupFilterDialog.OnSelectItemListener() {
            @Override
            public void OnClick(String type) {
                AppController.getInstance().getRestManager().getGroupService().getJoinGroup(type)
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
                switch (type) {
                    case GroupFilterDialog.FILTER_ALL:
                        txtvType.setText("전체보기");
                        break;
                    case GroupFilterDialog.FILTER_MINE:
                        txtvType.setText("내그룹");
                        break;

                }
            }
        }).show();
    }

}

