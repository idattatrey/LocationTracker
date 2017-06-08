package com.location.tracker;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class NotificationHandler extends BroadcastReceiver {
    private Context mContxt;
    private static final String TAG = NotificationHandler.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        mContxt = context;

        String action = intent.getAction(); //Get the button actions

        if (!TextUtils.isEmpty(action)) {
            int notifId = intent.getIntExtra(HomeActivity.EXTRA_NOTIFICATION_ID, 0);
            Log.v(TAG + "=======", "Notification id==" + notifId);
            Intent mintent = new Intent(context, LocationUpdateService.class);
            if (action.equalsIgnoreCase(HomeActivity.ACTION_STOP)) {
                Log.v(TAG + "=======", "Pressed YES");

                if (LocationUpdateService.isEnded) {
                    Log.v(TAG + "=======", "Service stopped.");
                    context.stopService(mintent);
                    Toast.makeText(context, "Service stopped.", Toast.LENGTH_SHORT).show();
                    cancelNotification(context);
                }
            }
        }

    }

    private void cancelNotification(Context mContext) {
        NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }

}
