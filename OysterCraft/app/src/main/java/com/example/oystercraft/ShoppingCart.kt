package com.example.oystercraft

// Singleton object to manage the shopping cart items
object ShoppingCart {
    // Mutable list to store CartItem objects
    private val items = mutableListOf<CartItem>()

    // Adds a new item to the cart
    fun addItem(item: CartItem) {
        // Check if the item already exists in the cart (by name)
        val existingItem = items.find { it.name == item.name }
        if (existingItem != null) {
            // If it exists, update its quantity
            val index = items.indexOf(existingItem)
            items[index] = existingItem.copy(quantity = existingItem.quantity + item.quantity)
        } else {
            // Otherwise, add the new item
            items.add(item)
        }
    }

    // Removes an item from the cart
    fun removeItem(item: CartItem) {
        items.remove(item)
    }

    // Clears all items from the cart
    fun clearCart() {
        items.clear()
    }

    // Returns a read-only list of current cart items
    fun getItems(): List<CartItem> {
        return items.toList() // Return a copy to prevent external modification
    }

    // Calculates the total price of all items in the cart
    fun getTotal(): Double {
        return items.sumOf { it.quantity * it.pricePerUnit }
    }
}