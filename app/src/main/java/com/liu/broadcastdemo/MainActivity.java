package com.liu.broadcastdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //自定义时间改变广播接收器
    class TimeChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"time has changed", Toast.LENGTH_SHORT).show();
        }
    }

    private TimeChangeReceiver timeChangeReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter();
        timeChangeReceiver = new TimeChangeReceiver();
        //时间变化广播Action
        intentFilter.addAction("android.intent.action.TIME_TICK");
        //注册广播
        registerReceiver(timeChangeReceiver, intentFilter);
        //=======按钮发送广播========
        findViewById(R.id.btn).setOnClickListener(v -> {
            Intent intent = new Intent("com.liu.broadcastdemo.MY_BROADCAST");
            //Android8.0+，指定包名转为显式广播，静态接收器才能收到
            intent.setPackage(getPackageName());

            //【标准广播】
//        sendBroadcast(intent);

            //【有序广播】，第二个参数为权限，传null
            sendOrderedBroadcast(intent,null);
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁时注销广播，防止内存泄漏
        unregisterReceiver(timeChangeReceiver);
    }
}
