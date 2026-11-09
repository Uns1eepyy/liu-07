package com.liu.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received a broadcast!", Toast.LENGTH_SHORT).show();
        //有序广播打开下面代码可以截断广播
//        Toast.makeText(context, "即将截断广播~!", Toast.LENGTH_SHORT).show();
//        abortBroadcast();
    }
}
