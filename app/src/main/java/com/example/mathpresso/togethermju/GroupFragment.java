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

        //서버에  그룹정보 요청
        //add mock data
        ArrayList<Group> items = new ArrayList<>();

        mAdapter = new GroupAdapter(items, getActivity(), new GroupAdapter.OnGroupSelectedListener() {
            @Override
            public void onSelect(Group group) {
                Toast.makeText(getActivity(),group.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }

}
