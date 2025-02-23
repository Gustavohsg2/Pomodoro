package com.example.pomodoro

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log

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
        val numEstudo = findViewById<EditText>(R.id.numEstudo)
        val btnStart = findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener(){
            val textoDoEditText = numEstudo.text.toString()
            val numero: Long? = textoDoEditText.toLongOrNull()
            if (numero != null) {
                Log.d("MainActivity", "Número: $numero")
            } else {
                Log.d("MainActivity", "Texto inválido, não é um número.")
            }
            if (numero != null) {
                vibrar(this, tempo=(numero*1000))
            }
        }
    }

    private fun vibrar(context: MainActivity, tempo: Long) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val vibrationEffect = VibrationEffect.createOneShot(tempo,
                VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(vibrationEffect)
        } else {
            vibrator.vibrate(tempo)
        }
    }
}