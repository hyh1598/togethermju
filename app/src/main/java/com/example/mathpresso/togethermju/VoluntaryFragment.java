package com.example.mathpresso.togethermju;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathpresso.togethermju.adapter.NoticeAdapter;
import com.example.mathpresso.togethermju.adapter.VoluntaryAdapter;
import com.example.mathpresso.togethermju.model.Contest;
import com.example.mathpresso.togethermju.model.Notice;
import com.example.mathpresso.togethermju.model.Voluntary;

import net.cachapa.expandablelayout.ExpandableLinearLayout;

import java.util.ArrayList;


/**
 * Created by choijinjoo on 2016. 11. 9..
 */
public class VoluntaryFragment extends Fragment {
    VoluntaryAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_voluntary, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //add mock data
        ArrayList<Voluntary> items = new ArrayList<>();
        items.add(new Voluntary(1,"해외 봉사활동","내용"));
        items.add(new Voluntary(2,"해외 봉사활동","내용"));
        items.add(new Voluntary(3,"해외 봉사활동","내용"));
        items.add(new Voluntary(4,"해외 봉사활동","내용"));
        items.add(new Voluntary(5,"해외 봉사활동","내용"));
        items.add(new Voluntary(6,"해외 봉사활동","내용"));
        items.add(new Voluntary(7,"해외 봉사활동","내용"));
        items.add(new Voluntary(8,"해외 봉사활동","내용"));
        items.add(new Voluntary(9,"해외 봉사활동","내용"));

        mAdapter = new VoluntaryAdapter(recyclerView, items, getActivity(), new VoluntaryAdapter.OnVoluntarySelectedListener() {
            @Override
            public void onSelect(Voluntary contest) {
                Toast.makeText(getActivity(),contest.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }

}
