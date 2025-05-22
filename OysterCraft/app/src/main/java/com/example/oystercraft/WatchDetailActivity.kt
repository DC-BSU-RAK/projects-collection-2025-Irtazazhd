package com.example.oystercraft

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WatchDetailActivity : AppCompatActivity() {

    // Define watch prices (example values, adjust as needed)
    private val watchPrices = mapOf(
        "Rolex Submariner" to 10000.0,
        "Rolex Daytona" to 15000.0,
        "Rolex GMT-Master II" to 12000.0,
        "Rolex Datejust" to 8000.0,
        "Rolex Sky-Dweller" to 18000.0,
        "Rolex Yacht-Master" to 11000.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_detail)

        // Get references to UI elements
        val watchImage: ImageView = findViewById(R.id.detailWatchImage)
        val watchName: TextView = findViewById(R.id.detailWatchName)
        val watchDescription: TextView = findViewById(R.id.detailWatchDescription)
        val watchPrice: TextView = findViewById(R.id.detailWatchPrice) // NEWLY ADDED
        val editTextQuantity: EditText = findViewById(R.id.editTextQuantity)
        val buttonBuy: Button = findViewById(R.id.buttonBuy)

        // Retrieve data passed from the Intent
        val intent = intent
        val name = intent.getStringExtra("WATCH_NAME") ?: "Unknown Watch" // Provide default
        val imageResId = intent.getIntExtra("WATCH_IMAGE_RES_ID", R.drawable.rolex1) // Default if not found
        val description = intent.getStringExtra("WATCH_DESCRIPTION") ?: "No description available." // Provide default

        // Get the price for the current watch
        val pricePerUnit = watchPrices[name] ?: 0.0 // Default to 0 if price not found

        // Populate UI with retrieved data
        watchImage.setImageResource(imageResId)
        watchName.text = name
        watchDescription.text = description
        // Populate the new price TextView
        watchPrice.text = String.format("AED %,.2f", pricePerUnit) // NEWLY ADDED


        // Set up Buy Button click listener
        buttonBuy.setOnClickListener {
            val quantityString = editTextQuantity.text.toString()
            if (quantityString.isEmpty()) {
                Toast.makeText(this, "Please enter a quantity.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantity = quantityString.toInt()
            if (quantity <= 0) {
                Toast.makeText(this, "Quantity must be greater than zero.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a CartItem object
            val cartItem = CartItem(name, quantity, pricePerUnit, imageResId)

            // Add the item to the ShoppingCart singleton
            ShoppingCart.addItem(cartItem)

            Toast.makeText(this, "Added $quantity x $name to cart!", Toast.LENGTH_SHORT).show()

            // Launch CartActivity
            val cartIntent = Intent(this, CartActivity::class.java)
            startActivity(cartIntent)
            // You can optionally finish this activity if you don't want it on the back stack
            // finish()
        }
    }
}