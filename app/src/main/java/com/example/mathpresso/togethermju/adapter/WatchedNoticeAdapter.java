package com.example.mathpresso.togethermju.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.base.CustomAdapter;
import com.example.mathpresso.togethermju.model.Notice;

import net.cachapa.expandablelayout.ExpandableLinearLayout;

import java.util.ArrayList;

public class WatchedNoticeAdapter extends CustomAdapter<Notice, WatchedNoticeAdapter.ViewHolder> {
    private static final int UNSELECTED = -1;
    OnNoticeSelectedListener mListener;
    Activity mActivity;



    public interface OnNoticeSelectedListener {
        public void onSelect(Notice notice);
    }

    public WatchedNoticeAdapter(RecyclerView recyclerView, ArrayList<Notice> data, Activity context, OnNoticeSelectedListener listener) {
        super(context, data);
        mListener = listener;
        mActivity = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_notice, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Notice item = mItems.get(position);
        holder.bind(position);
        holder.expandButton.setText(item.getTitle());
        holder.txtvContent.setText(item.getContent());
        holder.btnGoToActv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSelect(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnFocusChangeListener {

        private ExpandableLinearLayout expandableLayout;
        private TextView expandButton;
        private TextView txtvContent;
        private Button btnGoToActv;


        public ViewHolder(View itemView) {
            super(itemView);

            expandableLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandable_layout);
            expandButton = (TextView) itemView.findViewById(R.id.expand_button);
            txtvContent = (TextView) itemView.findViewById(R.id.txtvContent);
            btnGoToActv = (Button) itemView.findViewById(R.id.btnGoToActv);

            expandButton.setOnClickListener(this);
            expandButton.setFocusableInTouchMode(true);
            expandButton.setOnFocusChangeListener(this);
            btnGoToActv.setOnClickListener(this);
        }

        public void bind(int position) {
            expandButton.setText(position + ". Click to expand");
        }

        @Override
        public void onClick(View view) {
            if (expandButton.isFocused()) {
                expandButton.clearFocus();
            } else {
                expandButton.requestFocus();
            }
        }

        @Override
        public void onFocusChange(View view, boolean focused) {
            if (focused) {
                expandableLayout.expand();
            } else {
                expandableLayout.collapse();
            }
        }
    }
}