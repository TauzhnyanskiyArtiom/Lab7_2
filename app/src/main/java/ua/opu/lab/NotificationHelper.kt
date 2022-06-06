package ua.opu.lab

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

object NotificationHelper {

    fun sendNotification(
        context: Context,
        channelId: String,
        icon: Int,
        contentTitle: String,
        message: String
    ) {
        val builder = NotificationCompat.Builder(context,  channelId)
            .setSmallIcon(icon)
            .setContentTitle(contentTitle)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(context).apply {
            this.notify(Random.nextInt(), builder.build())
        }
    }
}