package com.example.seminarionotificacioneskotlinfranciscorequena

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.seminarionotificacioneskotlinfranciscorequena.MainActivity.Companion.create_notification
import com.example.seminarionotificacioneskotlinfranciscorequena.MainActivity.Companion.id

class Ejercicio2Activity2 : AppCompatActivity() {
    private lateinit var boton: AppCompatButton
    private lateinit var boton2: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio22)
        boton = findViewById(R.id.mostrar_notificacion)
        boton2 = findViewById(R.id.mostrar_notificacion2)
        boton.setOnClickListener {
            mostrarNotify(applicationContext)
        }
        boton2.setOnClickListener {
            mostrarNotify2(applicationContext)
        }

    }
    fun mostrarNotify(context: Context) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channelId= id.toString()
            val channelName="Mi canal"
            val notificationChannel= NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_DEFAULT)
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)


        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder= NotificationCompat.Builder(context, id.toString())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Hace mucho frio")
            // BigTextStyle
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Abrigate o te pondrás malo. Mi id es $id , " +
                        //habla sobre el frio
                        "El frío, una sensación que cala hasta los huesos, transforma el entorno en un paisaje gélido. El aire se vuelve cortante, y la humedad se congela en escarcha, pintando un lienzo blanco sobre la naturaleza. El cuerpo se estremece, buscando refugio en capas de abrigo. El frío no solo es una temperatura baja, sino una experiencia que nos recuerda nuestra fragilidad ante la fuerza de la naturaleza. Nos invita a buscar calor, a valorar el refugio y la compañía. En su abrazo helado, el frío nos enseña la belleza de la calidez. "))
            .setColor(ContextCompat.getColor(context,R.color.Morado))
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_launcher_foreground,"Iniciar Actividad",pendingIntent)

        with(context.getSystemService<NotificationManager>()){
            this?.notify(1,builder.build())
            create_notification()

        }

    }
    fun mostrarNotify2(context: Context) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channelId= id.toString()
            val channelName="Mi canal"
            val notificationChannel= NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_DEFAULT)
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)


        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder= NotificationCompat.Builder(context, id.toString())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Hace mucho frio")
            .setContentText("Abrigate o te pondrás malo. Mi id es $id")
            //BigPictureStyle

            .setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(ContextCompat.getDrawable(context,R.drawable.hielo)?.toBitmap())
                .bigLargeIcon(Icon.createWithResource(applicationContext,R.drawable.hielo)))

             .setColor(ContextCompat.getColor(context,R.color.Morado))
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_launcher_foreground,"Iniciar Actividad",pendingIntent)

        with(context.getSystemService<NotificationManager>()){
            this?.notify(1,builder.build())
            create_notification()

        }
    }
}
