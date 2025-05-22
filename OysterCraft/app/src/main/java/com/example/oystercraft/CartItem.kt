package com.example.oystercraft

import java.io.Serializable

// Data class to represent an item in the shopping cart
// It implements Serializable so it can be passed easily via Intents
data class CartItem(
    val name: String,
    val quantity: Int,
    val pricePerUnit: Double,
    val imageResId: Int // Resource ID for the watch image
) : Serializable