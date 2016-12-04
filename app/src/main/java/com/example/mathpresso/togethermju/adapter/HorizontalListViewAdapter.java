package com.example.mathpresso.togethermju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.model.User;

import java.util.ArrayList;

/**
 * Created by sonjiho on 2016. 12. 5..
 */

public class HorizontalListViewAdapter extends BaseAdapter {

    private Context mContext;
    private OnRecommandUserSelectedListener mListener;
    private ArrayList<User> userList = new ArrayList<User>();

    public HorizontalListViewAdapter(Context context,ArrayList<User> data,OnRecommandUserSelectedListener listener){
        mContext = context;
        mListener = listener;
        userList = data;
    }

    public interface OnRecommandUserSelectedListener {
        public void onSelect(User user);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_list_item, null);
        //list item view

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onSelect(userList.get(position));
            }
        });
        return view;
    }

}
