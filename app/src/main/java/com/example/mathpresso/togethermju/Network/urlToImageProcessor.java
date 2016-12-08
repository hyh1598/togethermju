package com.example.mathpresso.togethermju.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mathpresso.togethermju.core.AppController;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sonjiho on 2016. 12. 8..
 * user email 넣어주면 user 의 사진을 돌려줌
 * new urlToImageProcessor.execute([email])
 */

public class urlToImageProcessor extends AsyncTask<String,Void,Bitmap> {
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap imgBitmap = null;

        String server_url  = AppController.getBaseUrl()+"loaduserimage/?email="+params[0];
        Log.d("IMAGEPROCESSOR",server_url);
        //"http://125.130.223.88:8000/mju/"
        try{
            URL url = new URL(server_url);
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
