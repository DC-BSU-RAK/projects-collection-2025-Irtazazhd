package com.example.oystercraft

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getStartedBtn = findViewById<Button>(R.id.getStartedBtn)
        getStartedBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
