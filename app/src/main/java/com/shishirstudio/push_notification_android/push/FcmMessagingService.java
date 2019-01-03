package com.shishirstudio.push_notification_android.push;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.shishirstudio.push_notification_android.R;
import org.json.JSONObject;

public class FcmMessagingService extends FirebaseMessagingService {

    private final String TAG = FcmMessagingService.class.getSimpleName();
    private NotificationUtils mNotificationUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationUtils = new NotificationUtils(getApplicationContext());
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage == null) return;

        /*...................................................
            Handle Notification Payload
         ..................................................*/

        if (remoteMessage.getNotification() != null){
            Log.v(TAG, remoteMessage.getNotification().toString());
            handleNotificationPayloadMessage(remoteMessage.getNotification().getTitle(),
                               remoteMessage.getNotification().getBody());
        }

        /*...................................................
            Handle Data Payload
         ..................................................*/

        if (remoteMessage.getData() != null && remoteMessage.getData().size() > 0){
            try {
                JSONObject json = new JSONObject(remoteMessage.getData());
                handleDataPayloadMessage(json);
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }

    private void handleNotificationPayloadMessage(String title, String message){
        /* If app is in background, notification will be handled by fireBase itself.
        We have to show notification manually if app is in foreground. */

        if (!mNotificationUtils.isAppIsInBackground(getApplicationContext())){
            mNotificationUtils.showNotification(R.mipmap.ic_launcher,title,message,null);
        }
    }


    private void handleDataPayloadMessage(JSONObject notificationJson){

        String title = notificationJson.optString("title");
        String message = notificationJson.optString("message");
        String imageUrl = notificationJson.optString("image");
        final int smallIcon = R.mipmap.ic_launcher;

        mNotificationUtils.showNotification(smallIcon,
                title,
                message,
                imageUrl);
     }
}
