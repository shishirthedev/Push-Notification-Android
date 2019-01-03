package com.shishirstudio.push_notification_android.push;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FcmInstanceIdService extends FirebaseInstanceIdService {

    private final String TAG = FcmInstanceIdService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        storeRefreshTokenToServer(refreshToken);
        Log.d(TAG,"REFRESH-TOKEN: "+ refreshToken);
    }

    private void storeRefreshTokenToServer(String refreshToken) {

        // Store Refresh Token to Server.....
    }

}

