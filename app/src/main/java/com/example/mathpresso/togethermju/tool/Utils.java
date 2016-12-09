package com.example.mathpresso.togethermju.tool;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by sonjiho on 2016. 12. 8..
 */

public class Utils {
    public static boolean isEmpty(String str) {
        return str.trim().length() == 0;
    }

    public static okhttp3.RequestBody getImageBody(Map<String, String> map) {
        return getImageBodyBuilder(map).build();
    }
    public static RequestBody toRequestBody (String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body ;
    }

    public static MultipartBody.Builder getImageBodyBuilder(Map<String,String> map) {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String key : map.keySet()) {
            final String filePlace = map.get(key);
            final String[] args = filePlace.split("\\.");
            final String fileExt = args[args.length - 1];
            builder.setType(MultipartBody.FORM)
                    .addFormDataPart("photo", filePlace, okhttp3.RequestBody.create(MultipartBody.FORM, new File(map.get(key))))
                    .build();

        }


//        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
//        for (String key : map.keySet()) {
//            final String filePlace = map.get(key);
//            Log.d("multipart", "File place: " + filePlace);
//            final String[] args = filePlace.split("\\.");
//            final String fileExt = args[args.length - 1];
//            Log.d("multipart", "File extension: " + fileExt);
//
//            // Old solution, porting from one project to another
//            // It's about problems with jpeg
//            builder.addPart(Headers.of("Content-Disposition",
//                    "form-data; name=\"" + key + "\"; filename=\"" + filePlace + "\""), RequestBody.create(
//                    MediaType.parse("image/" + fileExt.toLowerCase().replace("jpg", "jpeg")),
//                    new File(map.get(key))));
//        }

        return builder;
    }

    public static String getPath(Context ctx, Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = ctx.getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(columnIndex);
        cursor.close();
        return s;
    }
}
