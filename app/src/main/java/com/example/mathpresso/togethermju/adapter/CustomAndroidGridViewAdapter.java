package com.example.mathpresso.togethermju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.model.User;

import java.util.ArrayList;

/**
 * Created by mk on 2016-12-04.
 */

/**
 * Created by HP on 5/11/2016.
 */

public class CustomAndroidGridViewAdapter extends BaseAdapter {
    private Context mContext;
    //user list 가지기
    private ArrayList<User> userlist = new ArrayList<User>();
    private OnUserSelectedListener mlistener;
    LayoutInflater inflater ;


    public CustomAndroidGridViewAdapter(Context c,ArrayList<User> data ,OnUserSelectedListener mlistener) {
        mContext = c;
        this.userlist = data;
        this.mlistener = mlistener;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public interface OnUserSelectedListener {
        public void onSelect(User user);
    }

    @Override
    public int getCount() {
        return userlist.size();
    }

    @Override
    public Object getItem(int p) {
        return userlist.get(p);
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
        final User user = userlist.get(p);
        TextView textView = (TextView) grid.findViewById(R.id.gridview_text);
        ImageView imageView = (ImageView)grid.findViewById(R.id.gridview_image);
        textView.setText(user.getName());
//        imageView.setImageResource(Imageid[p]);

        grid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mlistener.onSelect(user);
            }
        });

        return grid;
    }
}
