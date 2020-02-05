package BroadcastReceiver;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.example.onlinestore.R;

import Channels.Channels;

public class BroadcastReceiver extends android.content.BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadcastReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean noConnectivity;
        notificationManagerCompat=NotificationManagerCompat.from(context);
        Channels channels=new Channels(context);
        channels.createChannel();

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction()))
        {
            noConnectivity=intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY,
                    false);

            if (noConnectivity)
            {
                Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG).show();
                DisplayNotification();
            }
            else {
                Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
                DisplayNotification1();
            }
        }
    }

    private void DisplayNotification()
    {
        Notification notification=new NotificationCompat.Builder(context, Channels.CHANNEL_1)
                .setContentTitle("No Internet connection!!")
                .setContentText("Oops. Please Check your internet connection!! Try again later.")
                .setSmallIcon(R.drawable.accessoriessvg)
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();
        notificationManagerCompat.notify(1,notification);
    }

    private void DisplayNotification1()
    {
        Notification notification=new NotificationCompat.Builder(context,Channels.CHANNEL_2)
                .setContentTitle("You are connected to Internet.")
                .setContentText("Feel-good shopping.!!!")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .setSmallIcon(R.drawable.accessoriessvg)
                .build();
        notificationManagerCompat.notify(2,notification);
    }


}
