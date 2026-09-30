package com.kevin.implicit_intent

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnKirimPesan = findViewById<Button>(R.id.btnKirimPesan)
        val btnSetAlarm = findViewById<Button>(R.id.btnSetAlarm)
        val btnSetTimer = findViewById<Button>(R.id.btnSetTimer)

        btnKirimPesan.setOnClickListener {
            val _sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra("address", "0811234")
                putExtra("sms_body", "ISI SMS")
                type = "text/plain"
            }

            if (_sendIntent.resolveActivity(packageManager) != null) {
//            startActivity(_sendIntent)
                // untuk memaksa user memilih menggunakan intent chooser
                startActivity(Intent.createChooser(_sendIntent, "PILIH APLIKASI "))
            }
        }

        btnSetAlarm.setOnClickListener {

            val _alarmIntent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "COBA ALARM")
                putExtra(AlarmClock.EXTRA_HOUR, 20)
                putExtra(AlarmClock.EXTRA_MINUTES, 15)
                // buat alarm tanpa UI set alarm
//                putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            }
            startActivity(_alarmIntent)
        }

        btnSetTimer.setOnClickListener {

            Log.d("TIMER", "TIMER")
            val _timerIntent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "COBA TIMER")
                putExtra(AlarmClock.EXTRA_LENGTH, 20)
//                putExtra(AlarmClock.EXTRA_SKIP_UI, true)

            }
            startActivity(_timerIntent)
        }






    }
}