package ua.opu.lab.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

class AirplaneModeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val builder = context?.let {
                NotificationCompat.Builder(it, "3")
                    .setSmallIcon(
                        if (intent.getBooleanExtra("state", false)
                        ) com.google.android.material.R.drawable.ic_mtrl_checked_circle else com.google.android.material.R.drawable.ic_mtrl_chip_close_circle
                    )
                    .setContentTitle("Airplane mode state")
                    .setContentText(
                        if (intent.getBooleanExtra("state", false)
                        ) "Airplane mode is ON" else "Airplane mode is OFF"
                    )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }

            context?.let {
                NotificationManagerCompat.from(it).apply {
                    builder?.let { it1 -> this.notify(Random.nextInt(), it1.build()) }
                }
            }
        }
    }
}