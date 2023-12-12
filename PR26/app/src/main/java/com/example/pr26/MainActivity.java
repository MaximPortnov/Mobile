package com.example.pr26;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;

public class MainActivity extends AppCompatActivity {
    // Идентификатор уведомления
    private static final int NOTIFY_ID = 123;
    private int counter = 123;

    // Идентификатор канала
    private static String CHANNEL_ID = "Cat channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressLint("MissingPermission")
            public void onClick(View v) {
                //Uri ringURI =
                //      RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                Uri soundUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.meow);

                long[] vibrate = new long[] { 1000, 1000, 1000, 1000, 1000 };

                Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this,
                        0, notificationIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

                String bigText = "Это я, почтальон Печкин. Принёс для вас посылку. "
                        + "Только я вам её не отдам. Потому что у вас документов нету. ";


                Person murzik = new Person.Builder().setName("Мурзик").build();
                Person vaska = new Person.Builder().setName("Васька").build();

                NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle
                        (murzik)
                        .setConversationTitle("Android chat")
                        .addMessage("Привет котаны!", System.currentTimeMillis(), murzik)
                        .addMessage("А вы знали, что chat по-французски кошка?", System
                                        .currentTimeMillis(),
                                murzik)
                        .addMessage("Круто!", System.currentTimeMillis(),
                                vaska)
                        .addMessage("Ми-ми-ми", System.currentTimeMillis(), vaska)
                        .addMessage("Мурзик, откуда ты знаешь французский?", System.currentTimeMillis(),
                                vaska)
                        .addMessage("Шерше ля фам, т.е. ищите кошечку!", System.currentTimeMillis(),
                                murzik);



                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.kitty)
                                //.setSmallIcon(android.R.drawable.stat_sys_download)
                                .setContentTitle("Напоминание")
                                .setContentText("Пора покормить кота")
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setContentIntent(contentIntent)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                        R.drawable.cat))
                                .setAutoCancel(true)
                                .setSound(soundUri)
                                .setVibrate(vibrate)
                                .setLights(Color.RED,0,1)
                                .addAction(R.drawable.kitty, "Открыть", contentIntent)
                                .addAction(R.drawable.kitty, "Отказаться", contentIntent)
                                .addAction(R.drawable.kitty, "Другой вариант", contentIntent)
                                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));
//                                .setStyle(new NotificationCompat.BigPictureStyle()
//                                        .bigPicture(BitmapFactory.decodeResource(getResources(),
//                                                R.drawable.tom)))
//                                .setStyle(new NotificationCompat.InboxStyle()
//                                        .addLine("Первое сообщение").addLine("Второе сообщение")
//                                        .addLine("Третье сообщение").addLine("Четвертое сообщение")
//                                        .setSummaryText("+2 more"))
                                //.setStyle(messagingStyle)
//                                .setColor(Color.GREEN);


                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(MainActivity.this);
//                notificationManager.notify(NOTIFY_ID, builder.build());

                // Теперь у уведомлений будут новые идентификаторы
                notificationManager.notify(counter++, builder.build());

                // Удаляем конкретное уведомление
                //notificationManager.cancel(NOTIFY_ID);
                // Удаляем все свои уведомления
                //notificationManager.cancelAll();




            }

        });
    }
}