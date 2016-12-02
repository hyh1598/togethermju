package com.example.mathpresso.togethermju.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.base.CustomAdapter;
import com.example.mathpresso.togethermju.model.Group;

import net.cachapa.expandablelayout.ExpandableLinearLayout;

import java.util.ArrayList;

public class GroupAdapter extends CustomAdapter<Group, GroupAdapter.ViewHolder> {
    private static final int UNSELECTED = -1;
    OnGroupSelectedListener mListener;
    Activity mActivity;

    public GroupAdapter(RecyclerView recyclerView, ArrayList<Group> data, Activity context, OnGroupSelectedListener listener) {
        super(context, data);
        mListener = listener;
        mActivity = context;
    }


    public interface OnGroupSelectedListener {
        public void onSelect(Group group);
    }

    public GroupAdapter(ArrayList<Group> data, Activity context, OnGroupSelectedListener listener) {
        super(context, data);
        mListener = listener;
        mActivity = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_group, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Group item = mItems.get(position);
        holder.txtvName.setText(item.getName());


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ExpandableLinearLayout expandableLayout;
        private TextView expandButton;
        private TextView txtvName;
        private TextView btnGoToActv;


        public ViewHolder(View itemView) {
            super(itemView);
            txtvName = (TextView) itemView.findViewById(R.id.txtvName);
        }


    }
}