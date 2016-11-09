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

import com.example.mathpresso.togethermju.adapter.ContestAdapter;
import com.example.mathpresso.togethermju.model.Contest;


import java.util.ArrayList;


/**
 * Created by choijinjoo on 2016. 11. 9..
 */
public class ContestFragment extends Fragment {

    ContestAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contest, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // add mock data
        ArrayList<Contest> items = new ArrayList<>();
        items.add(new Contest(1,"해외관광 아이디어 공모전","해외관광 아이디어 공모전입니다. 지원하세요~"));
        items.add(new Contest(2,"IOT 아이디어 공모전","IOT 아이디어 공모전입니다. 지원하세요~"));
        items.add(new Contest(3," 공모전","내용"));
        items.add(new Contest(4,"공모전","내용"));
        items.add(new Contest(5,"공모전2","내용"));
        items.add(new Contest(6,"공모전3","내용"));
        items.add(new Contest(7,"공모전4","내용"));
        items.add(new Contest(8,"공모전5","내용"));
        items.add(new Contest(9,"공모전5","내용"));
        items.add(new Contest(10,"공모전6","내용"));
        items.add(new Contest(11,"공모전7","내용"));
        items.add(new Contest(12,"공모전8","내용"));
        items.add(new Contest(13,"공모전9","내용"));
        items.add(new Contest(14,"공모전10","내용"));
        items.add(new Contest(15,"공모전11","내용"));

        mAdapter = new ContestAdapter(recyclerView, items, getActivity(), new ContestAdapter.OnContestSelectedListener() {
            @Override
            public void onSelect(Contest contest) {
                Toast.makeText(getActivity(),contest.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
