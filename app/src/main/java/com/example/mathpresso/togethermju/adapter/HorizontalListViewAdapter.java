package com.example.mathpresso.togethermju.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.User;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by sonjiho on 2016. 12. 5..
 */

public class HorizontalListViewAdapter extends BaseAdapter {

    private Context mContext;
    private OnMemberSelectedListener mListener;
    private ArrayList<User> userList = new ArrayList<User>();

    public HorizontalListViewAdapter(Context context, ArrayList<User> data, OnMemberSelectedListener listener) {
        mContext = context;
        mListener = listener;
        userList = data;
    }

    public interface OnMemberSelectedListener {
        public void onSelect(User user);
    }

    public void add(List<User> ulist) {
        userList.addAll(ulist);
        notifyDataSetChanged();
    }

    public void clear() {
        userList.clear();
        notifyDataSetChanged();
    }

    public Context getActivity() {
        return mContext;
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
        MemberViewHolder viewHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list_item, null);
            viewHolder = new MemberViewHolder();

            viewHolder.icon = (ImageView) convertView.findViewById(R.id.member_image_view);

            convertView.setTag(viewHolder);

        }

        viewHolder = (MemberViewHolder) convertView.getTag();

        String email = userList.get(position).getEmail();
        String server_url = AppController.getBaseUrl() + "loaduserimage/?email=" + email;


        Glide.with(mContext).load(server_url)
                .fitCenter()
                .bitmapTransform(new CropCircleTransformation(mContext))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(viewHolder.icon);

        //new imageViewProcessor().execute(viewHolder);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSelect(userList.get(position));
            }
        });

        return convertView;
    }

    public class MemberViewHolder {
        public Bitmap bitmap;
        public ImageView icon;
        public String email;
    }

    //image 처리
    private class imageViewProcessor extends AsyncTask<MemberViewHolder, Void, MemberViewHolder> {

        @Override
        protected MemberViewHolder doInBackground(MemberViewHolder... params) {

            MemberViewHolder memberViewHolder = params[0];

            String server_url = AppController.getBaseUrl() + "loaduserimage/?email=" + memberViewHolder.email;
            Log.d("IMAGEPROCESSOR", server_url);
            //"http://125.130.223.88:8000/mju/"
            try {
                URL url = new URL(server_url);
                URLConnection conn = url.openConnection();
                conn.connect();
                int nSize = conn.getContentLength();
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(), nSize);
                memberViewHolder.bitmap = BitmapFactory.decodeStream(bis);
                bis.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return memberViewHolder;
        }

        @Override
        protected void onPostExecute(MemberViewHolder memberViewHolder) {
            super.onPostExecute(memberViewHolder);
            Log.d("IMAGE_MAPPING", "SUCCESS");
            memberViewHolder.icon.setImageBitmap(memberViewHolder.bitmap);

        }
    }


}
