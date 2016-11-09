package com.example.mathpresso.togethermju.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathpresso3 on 2015-09-04.
 */

public abstract class CustomAdapter <T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected final Context mContext;
    protected List<T> mItems;

    public void add(T item) {
        int position = getItemCount();
        mItems.add(item);
        notifyItemInserted(position);
    }

    public void add(List<T> items) {
        int position = getItemCount();
        for (T item : items) {
            mItems.add(item);
        }
        notifyItemRangeInserted(position, items.size());
    }

    public void remove(int position){
        if (position < getItemCount() ) {
            mItems.remove(position);
            notifyDataSetChanged();
        }
    }
    public void remove(T item){
        int position = mItems.indexOf(item);
        remove(position);
    }

    public void clear(){
        int count = mItems.size();
        if(count > 0) {
            mItems.clear();
            notifyDataSetChanged();
        }
    }

    public CustomAdapter(Context context, List<T> data) {
        mContext = context;
        if (data != null) {
            //mItems = new ArrayList<T>(data);
            mItems = data;
        }
        else mItems = new ArrayList<T>();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public List<T> getItems() {
        return mItems;
    }
}