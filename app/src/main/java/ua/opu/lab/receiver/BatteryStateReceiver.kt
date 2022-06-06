package ua.opu.lab.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ua.opu.lab.NotificationHelper
import kotlin.random.Random

class BatteryStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BATTERY_LOW) {
            context?.let {
                NotificationHelper.sendNotification(
                    it,
                    CHANNEL_ID,
                    android.R.drawable.ic_lock_idle_low_battery,
                    "Battery state alert",
                    "LOW battery"
                )
            }

        }
    }

    companion object{
        const val CHANNEL_ID = "1"
    }
}