package com.example.mathpresso.togethermju.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mathpresso.togethermju.GroupDetailsActivity;

public class GCMBroadcastReceiver extends BroadcastReceiver {
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
                Log.d("jiho",message);
                sendToActivity(context,message);
            }
        }else{
            Log.d("jiho","actionNULL");
        }

    }
    private void sendToActivity(Context context,String message){
        Intent intent = new Intent(context, GroupDetailsActivity.class);
        intent.putExtra("message",message);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}
