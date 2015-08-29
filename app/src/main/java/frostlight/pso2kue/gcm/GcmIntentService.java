package frostlight.pso2kue.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.logging.Level;
import java.util.logging.Logger;

import frostlight.pso2kue.MainActivity;
import frostlight.pso2kue.R;
import frostlight.pso2kue.TranslationHelper;
import frostlight.pso2kue.Utility;

/**
 * Created by Vincent on 8/5/2015.
 */
public class GcmIntentService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        if (extras != null && !extras.isEmpty()) {  // has effect of unparcelling Bundle
            // Since we're not using two way messaging, this is all we really to check for
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                Logger.getLogger("GCM_RECEIVED").log(Level.INFO, extras.toString());

                // Save the registration ID if it was not registered for some reason
                String regId = intent.getExtras().getString("registration_id");
                if (regId != null && !regId.equals("") && GcmHelper.getRegistrationId(
                        getApplicationContext()).equals("")) {
                    GcmHelper.setRegistrationId(getApplicationContext(), regId);
                }

                // Display the push notification using the notification manager service
                String eqName = TranslationHelper.getEqTranslation(getApplicationContext(),
                        extras.getString("message"));

                mNotificationManager = (NotificationManager)
                        this.getSystemService(Context.NOTIFICATION_SERVICE);

                PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                        new Intent(this, MainActivity.class), 0);

                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.ic_notify_eq)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                .setContentTitle("[Ship " + Utility.getPreferenceShip(getApplicationContext())
                                        + "] " + eqName)
                                .setContentInfo(getString(R.string.list_item_eq_approaching))
                                .setSound(soundUri)
                                .setAutoCancel(true);
                mBuilder.setContentIntent(contentIntent);
                mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}