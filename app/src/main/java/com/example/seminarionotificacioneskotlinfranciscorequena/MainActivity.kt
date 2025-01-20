package com.example.seminarionotificacioneskotlinfranciscorequena

import android.Manifest.permission.POST_NOTIFICATIONS
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.concurrent.atomic.AtomicInteger

class MainActivity : AppCompatActivity() {
        private lateinit var boton : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        boton=findViewById(R.id.lanzarnoti)

        boton.setOnClickListener{
           mostrarNotify(applicationContext)

        }

        val requestPermissionLauncher =  registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

        when {
            ContextCompat.checkSelfPermission(
                applicationContext,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, POST_NOTIFICATIONS) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.

            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    POST_NOTIFICATIONS)
            }
        }
    }





    fun mostrarNotify(context: Context){

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channelId= id.toString()
            val channelName="Mi canal"
            val notificationChannel=NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_DEFAULT)
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)


        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder= NotificationCompat.Builder(context,id.toString())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Hace mucho frio")
            .setContentText("Abrigate o te pondrás malo. Mi id es $id")
            .setColor(ContextCompat.getColor(context,R.color.Morado))
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_launcher_foreground,"Iniciar Actividad",pendingIntent)

        with(context.getSystemService<NotificationManager>()){
            this?.notify(1,builder.build())
            create_notification()

        }
    }
    companion object{
        val APP_ID="com.example.seminarionotificacioneskotlinfranciscorequena"
        val NOTIFICATION_ID="${APP_ID}_c1"
        val id=AtomicInteger(0)
        fun create_notification(): Int {
            return id.incrementAndGet()
        }
    }
}
