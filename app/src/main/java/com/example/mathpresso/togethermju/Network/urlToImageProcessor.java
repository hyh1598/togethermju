package com.example.mathpresso.togethermju.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sonjiho on 2016. 12. 8..
 */

public class urlToImageProcessor extends AsyncTask<String,Void,Bitmap> {
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap imgBitmap = null;
        String data = params[0];
        try{
            URL url = new URL(data);
            URLConnection conn = url.openConnection();
            conn.connect();
            int nSize = conn.getContentLength();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(),nSize);
            imgBitmap = BitmapFactory.decodeStream(bis);

            bis.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return imgBitmap;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

}
