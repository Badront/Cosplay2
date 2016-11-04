package ru.badr.cosplay2.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.Html;

import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

import ru.badr.base.util.Navigate;
import ru.badr.cosplay2.activity.MainActivity;
import ru.badr.cosplay2.api.JuryEntity;
import ru.badr.cosplay2.api.JurySectionEntity;
import ru.badr.cosplay2.fragment.JuryFragment;
import ru.badr.opencon.R;

/**
 * Created by Badr
 * on 29.10.2016 14:01.
 */

public final class NotificationUtils {
    public static final int NOTIFICATION_ID = 112;

    private static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static void showNotification(Context context, JuryEntity juryEntity) {
        NotificationCompat.Builder builder = createBuilder(context, context.getString(R.string.new_jury_available), juryEntity);
        getNotificationManager(context).notify(NOTIFICATION_ID, builder.build());
    }

    @SuppressWarnings("deprecation")
    private static NotificationCompat.Builder createBuilder(
            Context context,
            String title,
            JuryEntity jury
    ) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(title);
        builder.setContentText(jury.getName());
        builder.setTicker(title);
        builder.setAutoCancel(true);

        Intent juryIntent = new Intent(context, MainActivity.class);
        juryIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        juryIntent.putExtra(Navigate.PARAM_CLASS, JuryFragment.class.getName());
        juryIntent.putExtra(Navigate.PARAM_ARGS, new Bundle());

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, juryIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        builder.setDefaults(Notification.DEFAULT_SOUND |
                Notification.DEFAULT_VIBRATE);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        try {
            NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
            style.setSummaryText(Html.fromHtml(jury.getDescription()));
            style.setBigContentTitle(jury.getName());

            style.bigPicture(Glide.with(context).load(jury.getLargeImage()).asBitmap().into(300, 300).get());

            builder.setLargeIcon(Glide.with(context).load(jury.getImage()).asBitmap().into(300, 300).get());
            //style.bigPicture(BitmapFactory.decodeStream((InputStream) new URL(jury.getImage()).getContent()));
            builder.setStyle(style);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(Html.fromHtml(jury.getDescription())));
        }
        builder.setOngoing(false);
        builder.setOnlyAlertOnce(true);
        return builder;
    }

    public static void scheduleNotifications(Context context, JurySectionEntity.List sections) {

    }
}
