package com.example.mathpresso.togethermju.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.base.CustomAdapter;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.GroupReply;
import com.example.mathpresso.togethermju.model.Notice;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sonjiho on 2016. 12. 6..
 */


public class ReplyListViewAdapter extends CustomAdapter<GroupReply, ReplyListViewAdapter.ViewHolder> {
    OnReplySelectedListener mListener;
    Activity mActivity;
    //image cash
    HashMap<String,Bitmap> imgCashMap = new HashMap<String,Bitmap>();

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
        Bitmap tempimg;

        final GroupReply item = mItems.get(position);

        holder.txtvName.setText(item.getName());

        holder.date.setText(item.getDate());
        holder.txtvContent.setText(item.getContent());

        String server_url = AppController.getBaseUrl() + "loaduserimage/?email=" + item.getEmail();


//        Glide.with(mContext).load(server_url)
//                .fitCenter()
//                .bitmapTransform(new CropCircleTransformation(mContext))
//                .into(holder.icon);

        holder.email = item.getEmail();
        //imaging cash
        if((tempimg = imgCashMap.get(holder.email))!=null){
            holder.icon.setImageBitmap(tempimg);
        }else{
            new ReplyListViewAdapter.imageViewProcessor().execute(holder);
        }


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        String email;
        ImageView icon;
        Bitmap bitmap;
        TextView txtvContent;
        TextView txtvName;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView)itemView.findViewById(R.id.image);
            txtvName = (TextView) itemView.findViewById(R.id.writerName);
            txtvContent = (TextView) itemView.findViewById(R.id.txtvContent);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
    //image 처리
    private class imageViewProcessor extends AsyncTask<ReplyListViewAdapter.ViewHolder, Void, ReplyListViewAdapter.ViewHolder> {


        @Override
        protected ReplyListViewAdapter.ViewHolder doInBackground(ReplyListViewAdapter.ViewHolder... params) {

            ReplyListViewAdapter.ViewHolder ViewHolder = params[0];


            String server_url = AppController.getBaseUrl() + "loaduserimage/?email=" + ViewHolder.email;
            Log.d("IMAGEPROCESSOR", server_url);
            //"http://125.130.223.88:8000/mju/"
            try {

                URL url = new URL(server_url);
                URLConnection conn = url.openConnection();
                conn.connect();
                int nSize = conn.getContentLength();
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(), nSize);
                ViewHolder.bitmap = BitmapFactory.decodeStream(bis);
                bis.close();

            } catch (Exception e) {

                e.printStackTrace();
            }
            return ViewHolder;
        }

        @Override
        protected void onPostExecute(ReplyListViewAdapter.ViewHolder ViewHolder) {
            super.onPostExecute(ViewHolder);
            Log.d("IMAGE_MAPPING", "SUCCESS");
            ViewHolder.icon.setImageBitmap(ViewHolder.bitmap);
            imgCashMap.put(ViewHolder.email,ViewHolder.bitmap);
        }
    }
}
