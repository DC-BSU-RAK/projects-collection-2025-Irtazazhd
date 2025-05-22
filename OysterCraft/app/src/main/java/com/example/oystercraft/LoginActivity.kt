package com.example.oystercraft

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.loginButton)
        val guestButton: Button = findViewById(R.id.guestButton)
        val emailInput: EditText = findViewById(R.id.emailInput)
        val passwordInput: EditText = findViewById(R.id.passwordInput)

        // Login Button Click Listener
        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Simulated authentication (can be replaced with actual logic)
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // If login is successful
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                // Navigate to GalleryActivity (Third Page)
                val intent = Intent(this, GalleryActivity::class.java)
                startActivity(intent)
                finish() // Prevent going back to LoginActivity
            } else {
                // Show error message if fields are empty
                Toast.makeText(this, "Please enter both Email and Password", Toast.LENGTH_SHORT).show()
            }
        }

        // Guest Button Click Listener (skip login)
        guestButton.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
            finish() // Prevent going back to LoginActivity
        }
    }
}
