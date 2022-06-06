package ua.opu.lab.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ua.opu.lab.NotificationHelper
import kotlin.random.Random

class AirplaneModeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            context?.let {
                val icon = if (intent.getBooleanExtra("state", false))
                    com.google.android.material.R.drawable.ic_mtrl_checked_circle
                else
                    com.google.android.material.R.drawable.ic_mtrl_chip_close_circle

                val message = if (intent.getBooleanExtra("state", false))
                    "Airplane mode is ON"
                else
                    "Airplane mode is OFF"

                NotificationHelper.sendNotification(
                    it,
                    CHANNEL_ID,
                    icon,
                    "Airplane mode state",
                    message
                )
            }

        }
    }

    companion object{
        const val CHANNEL_ID = "3"
    }
}