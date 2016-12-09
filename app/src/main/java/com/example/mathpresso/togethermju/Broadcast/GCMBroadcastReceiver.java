package com.example.mathpresso.togethermju.Broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.mathpresso.togethermju.MainActivity;
import com.example.mathpresso.togethermju.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class GCMBroadcastReceiver extends BroadcastReceiver {


    Context mContext;
    String sMsg = "";
    private NotificationManager mNotificationManager;

    public GCMBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        Log.d("TAG","action : "+action);
        if(action != null){
            if(action.equals("com.google.android.c2dm.intent.RECEIVE")){
                String message = intent.getStringExtra("message");
                String content = intent.getStringExtra("group");
                Log.d("jiho",message);
                notification(context,message,content);
            }
        }else{
            Log.d("jiho","actionNULL");
        }


    }


    private void notification(Context context,String message,String content ){
        NotificationManager noManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        // NotificationID, PendingIntent
        Intent noIntent = new Intent(context,MainActivity.class);

        PendingIntent pendIntent = PendingIntent.getActivity(context, 0, noIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle("[GROUP] "+content)
                .setContentText("RE: "+message)
                .setSmallIcon(R.drawable.myongji2)
                .setContentIntent(pendIntent)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND);

        Notification no = builder.build();
        noManager.notify(11, no);
    }

    private void sendToActivity(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(context, GroupDetailsActivity.class);
//        Group group = new Group();
//        //message parsing 후 데이터에 담아 전송
//        intent.putExtra("message",message);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        context.startActivity(intent);
    }
}
