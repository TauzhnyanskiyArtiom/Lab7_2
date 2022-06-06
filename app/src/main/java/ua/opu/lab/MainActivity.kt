package ua.opu.lab

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import ua.opu.lab.receiver.AirplaneModeReceiver
import ua.opu.lab.receiver.BatteryStateReceiver
import ua.opu.lab.receiver.CameraButtonReceiver

class MainActivity : AppCompatActivity() {

    private val batteryStateReceiver = BatteryStateReceiver()
    private val cameraButtonReceiver = CameraButtonReceiver()
    private val airplaneModeReceiver = AirplaneModeReceiver()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Log.d(MainActivity::class.simpleName, "Permission result: $it")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val batteryInfoChannel =
                NotificationChannel("1", "Battery state", NotificationManager.IMPORTANCE_LOW)
            val airplaneModeChannel =
                NotificationChannel(
                    "2",
                    "Camera button clicked",
                    NotificationManager.IMPORTANCE_HIGH
                )
            val cameraButtonChannel =
                NotificationChannel("3", "Airplane mode state", NotificationManager.IMPORTANCE_HIGH)

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(batteryInfoChannel)
            notificationManager.createNotificationChannel(airplaneModeChannel)
            notificationManager.createNotificationChannel(cameraButtonChannel)

        }
    }

    override fun onStart() {
        super.onStart()
        val batteryStateFilter = IntentFilter(Intent.ACTION_BATTERY_LOW)
        val cameraButtonFilter = IntentFilter(Intent.ACTION_CAMERA_BUTTON)
        val airplaneModeFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        registerReceiver(batteryStateReceiver, batteryStateFilter)
        registerReceiver(cameraButtonReceiver, cameraButtonFilter)
        registerReceiver(airplaneModeReceiver, airplaneModeFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(batteryStateReceiver)
        unregisterReceiver(cameraButtonReceiver)
        unregisterReceiver(airplaneModeReceiver)
    }

    override fun onResume() {
        super.onResume()
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }
}