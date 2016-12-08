package com.example.mathpresso.togethermju.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.base.CustomAdapter;
import com.example.mathpresso.togethermju.model.GroupReply;
import com.example.mathpresso.togethermju.model.Notice;

import java.util.ArrayList;

/**
 * Created by sonjiho on 2016. 12. 6..
 */

public class ReplyListViewAdapter extends CustomAdapter<GroupReply, ReplyListViewAdapter.ViewHolder> {
    OnReplySelectedListener mListener;
    Activity mActivity;

    public interface OnReplySelectedListener {
        public void onSelect(Notice notice);
    }

    public ReplyListViewAdapter(ArrayList<GroupReply> data, Activity context, ReplyListViewAdapter.OnReplySelectedListener listener) {
        super(context, data);
        mListener = listener;
        mActivity = context;
    }

    @Override
    public ReplyListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_reply, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReplyListViewAdapter.ViewHolder holder, int position) {
        final GroupReply item = mItems.get(position);

        holder.txtvName.setText(item.getName());
        holder.date.setText(item.getDate());
        holder.txtvContent.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtvContent;
        TextView txtvName;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            txtvName = (TextView) itemView.findViewById(R.id.writerName);
            txtvContent = (TextView) itemView.findViewById(R.id.txtvContent);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
