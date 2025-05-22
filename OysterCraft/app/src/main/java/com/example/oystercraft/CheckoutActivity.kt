package com.example.oystercraft

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint // Import for @SuppressLint

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val buttonBackToHome: Button = findViewById(R.id.buttonBackToHome)

        buttonBackToHome.setOnClickListener {
            // Clear the cart when returning to home after checkout
            ShoppingCart.clearCart()

            // Navigate back to the GalleryActivity and clear the activity stack
            val intent = Intent(this, GalleryActivity::class.java)
            // FLAG_ACTIVITY_CLEAR_TOP: If the activity being launched is already running in the current task,
            // then instead of launching a new instance of that activity, all of the other activities on top of it are closed
            // and this Intent is delivered to the (now on top) old activity.
            // FLAG_ACTIVITY_NEW_TASK: Starts the activity in a new task.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish() // Finish CheckoutActivity
        }
    }

    // Suppress the lint warning about missing super call.
    // We intentionally do NOT call super.onBackPressed() because we are
    // completely overriding the default back button behavior to navigate
    // directly to the Gallery and clear the activity stack.
    @SuppressLint("MissingSuperCall")
    // Suppress the deprecation warning for overriding onBackPressed().
    // This method is deprecated in newer APIs, but we are using it for a specific
    // navigation flow and suppressing the warning.
    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        val intent = Intent(this, GalleryActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish() // Finish CheckoutActivity
    }
}