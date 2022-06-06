package ua.opu.lab.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ua.opu.lab.NotificationHelper
import kotlin.random.Random

class CameraButtonReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_CAMERA_BUTTON) {
            context?.let {
                NotificationHelper.sendNotification(
                    it,
                    CHANNEL_ID,
                    android.R.drawable.radiobutton_on_background,
                    "Camera button click",
                    "Camera button was PRESSED"
                )
            }
        }
    }

    companion object{
        const val CHANNEL_ID = "2"
    }
}