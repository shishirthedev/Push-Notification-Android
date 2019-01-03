package com.shishirstudio.push_notification_android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.shishirstudio.push_notification_android.R;
import com.shishirstudio.push_notification_android.push.NotificationUtils;

public class MainActivity extends AppCompatActivity {

    NotificationUtils notificationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("TOKEN", FirebaseInstanceId.getInstance().getToken());
        notificationUtils= new NotificationUtils(this);
        if (getIntent() != null){
            int notificationId = getIntent().getIntExtra("notificationId",-1);
            if(notificationId != -1){
                notificationUtils.getNotificationManager().cancel(notificationId);
            }
        }
    }
}
