package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mathpresso.togethermju.adapter.GroupAdapter;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by choijinjoo on 2016. 11. 9..
 */

public class GroupFragment extends Fragment {
    GroupAdapter mAdapter;

    TextView txtvType;

    @Override
    public void onStart() {
        super.onStart();
        loadJoinGroupList();
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //add mock data


        mAdapter = new GroupAdapter(recyclerView, null, getActivity(), new GroupAdapter.OnGroupSelectedListener() {
            @Override
            public void onSelect(Group group) {

                //when Group Item Clicked, go to GroupDetailActivity.class
                Intent intent = new Intent(getActivity(), GroupDetailsActivity.class);

                intent.putExtra("group", group);//send selected Group object.
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);


        loadJoinGroupList();
        return rootView;
    }


    private void loadJoinGroupList() {
        //FIXME id값으로 바꿔야함
        AppController.getInstance().getRestManager().getGroupService().getJoinGroup(AppController.user.getEmail())
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
                        Log.d("ServerFailure",t.getMessage());
                    }
                });
    }






}

