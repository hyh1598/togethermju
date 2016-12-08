package com.example.mathpresso.togethermju.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.adapter.GroupAdapter;
import com.example.mathpresso.togethermju.model.Group;

import java.util.ArrayList;


/**
 * Created by choijinjoo on 2016. 11. 9..
 */

public class GroupFragment extends Fragment {
    GroupAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //add mock data
        ArrayList<Group> items = new ArrayList<>();
        items.add(new Group(1,"해외 봉사활동 그룹","해외 봉사활동 그룹"));
        items.add(new Group(2,"해외 봉사활동 그룹","해외 봉사활동 그룹"));
        items.add(new Group(3,"해외 봉사활동 그룹","해외 봉사활동 그룹"));
        items.add(new Group(4,"해외 봉사활동 그룹","해외 봉사활동 그룹"));
        items.add(new Group(5,"해외 봉사활동 그룹","해외 봉사활동 그룹"));
        items.add(new Group(6,"해외 봉사활동 그룹","해외 봉사활동 그룹"));
        items.add(new Group(7,"해외 봉사활동 그룹","해외 봉사활동 그룹"));

        mAdapter = new GroupAdapter(recyclerView, items, getActivity(), new GroupAdapter.OnGroupSelectedListener() {
            @Override
            public void onSelect(Group group) {
                Toast.makeText(getActivity(),group.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }

}
