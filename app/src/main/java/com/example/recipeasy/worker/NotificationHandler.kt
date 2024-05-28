package com.example.recipeasy.worker

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.recipeasy.R

const val MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS = 1

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun sendNotification(title: String, text: String, context: Context) {
    val channelId = "simple_notification"
    val notificationId = 1

    // Create a NotificationChannel if API level is 26 (Oreo) or higher
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = context.getString(R.string.channel_name)
        val descriptionText = context.getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    // Check if the app has the necessary permission
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
        != PackageManager.PERMISSION_GRANTED) {
        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity,
                Manifest.permission.POST_NOTIFICATIONS)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            AlertDialog.Builder(context)
                .setTitle("Permission needed")
                .setMessage("This app needs to post notifications to make you aware of certain events.")
                .setPositiveButton("ok") { dialog, which ->
                    ActivityCompat.requestPermissions(context,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS
                    )
                }
                .setNegativeButton("cancel") { dialog, which -> dialog.dismiss() }
                .create().show()
        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(context,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS
            )

            // MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    } else {
        // Permission has already been granted
        // Build the notification
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.eggs)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Show the notification
        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, builder.build())
        }
    }
}