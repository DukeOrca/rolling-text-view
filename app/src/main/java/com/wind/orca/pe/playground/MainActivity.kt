package com.wind.orca.pe.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.wind.orca.pe.rollingtextview.RollingTextView

class MainActivity : AppCompatActivity() {
    private var currentItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollingTextView = findViewById<RollingTextView>(R.id.rolling_text_view)

        findViewById<Button>(R.id.button_increase)?.setOnClickListener {
            currentItem++
            rollingTextView.currentItem = currentItem
        }

        findViewById<Button>(R.id.button_decrease)?.setOnClickListener {
            currentItem--
            rollingTextView.currentItem = currentItem
        }
    }
}