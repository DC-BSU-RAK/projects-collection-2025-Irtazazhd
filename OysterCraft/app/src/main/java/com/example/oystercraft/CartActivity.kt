package com.example.oystercraft

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartTotalTextView: TextView
    private lateinit var cartRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartTotalTextView = findViewById(R.id.cartTotalTextView)
        cartRecyclerView = findViewById(R.id.cartRecyclerView)
        val buttonCheckout: Button = findViewById(R.id.buttonCheckout)
        val buttonContinueShopping: Button = findViewById(R.id.buttonContinueShopping)
        val buttonClearCart: Button = findViewById(R.id.buttonClearCart)

        // Setup RecyclerView
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        cartAdapter = CartAdapter(ShoppingCart.getItems().toMutableList()) { itemToRemove ->
            // Callback when an item's remove button is clicked
            ShoppingCart.removeItem(itemToRemove)
            updateCartUI() // Refresh the UI after removal
            Toast.makeText(this, "${itemToRemove.name} removed from cart.", Toast.LENGTH_SHORT).show()
        }
        cartRecyclerView.adapter = cartAdapter

        // Initial UI update
        updateCartUI()

        buttonCheckout.setOnClickListener {
            if (ShoppingCart.getItems().isNotEmpty()) {
                // Keep this Toast for feedback, but add the Intent below it
                Toast.makeText(this, "Proceeding to checkout with total: AED %.2f".format(ShoppingCart.getTotal()), Toast.LENGTH_LONG).show()

                // --- START OF FIX ---
                // Create an Intent to open the CheckoutActivity
                val checkoutIntent = Intent(this, CheckoutActivity::class.java)
                // Start the CheckoutActivity
                startActivity(checkoutIntent)
                // Optionally finish this activity so the user can't go back to the cart directly
                finish()
                // --- END OF FIX ---
            } else {
                Toast.makeText(this, "Your cart is empty. Please add items first.", Toast.LENGTH_SHORT).show()
            }
        }

        buttonContinueShopping.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        buttonClearCart.setOnClickListener {
            if (ShoppingCart.getItems().isNotEmpty()) {
                ShoppingCart.clearCart()
                updateCartUI()
                Toast.makeText(this, "Cart has been cleared.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Cart is already empty.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to update the cart UI (items list and total)
    private fun updateCartUI() {
        cartAdapter.updateItems(ShoppingCart.getItems())
        cartTotalTextView.text = String.format("Total: AED %,.2f", ShoppingCart.getTotal())

        // Show a message if cart is empty
        if (ShoppingCart.getItems().isEmpty()) {
            // You could show a specific empty cart message view here
            // For now, the RecyclerView will just be empty.
        }
    }

    // Override onResume to update UI in case user navigates back from detail page
    override fun onResume() {
        super.onResume()
        updateCartUI()
    }
}