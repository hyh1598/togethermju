package com.example.mathpresso.togethermju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathpresso.togethermju.R;

/**
 * Created by mk on 2016-12-04.
 */

/**
 * Created by HP on 5/11/2016.
 */

public class CustomAndroidGridViewAdapter extends BaseAdapter {
    private Context mContext;
    //user list 가지기
    private String[] string;
    private int[] Imageid;
    private OnUserSelectedListener mlistener;
    LayoutInflater inflater ;


    public CustomAndroidGridViewAdapter(Context c,String[] string,int[] Imageid ,OnUserSelectedListener mlistener) {
        mContext = c;
        this.Imageid = Imageid;
        this.string = string;
        this.mlistener = mlistener;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public interface OnUserSelectedListener {
        public void onSelect(String name);
    }

    @Override
    public int getCount() {
        return string.length;
    }

    @Override
    public Object getItem(int p) {
        return string[p];
    }

    @Override
    public long getItemId(int p) {
        return p;
    }

    @Override
    public View getView(int p, View convertView, ViewGroup parent) {
        View grid = convertView;


        if (convertView == null) {
            grid = inflater.inflate(R.layout.gridview_custom_layout, null);
        }
        final String name = string[p];
        TextView textView = (TextView) grid.findViewById(R.id.gridview_text);
        ImageView imageView = (ImageView)grid.findViewById(R.id.gridview_image);
        textView.setText(string[p]);
        imageView.setImageResource(Imageid[p]);

        grid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mlistener.onSelect(name);
            }
        });

        return grid;
    }
}
