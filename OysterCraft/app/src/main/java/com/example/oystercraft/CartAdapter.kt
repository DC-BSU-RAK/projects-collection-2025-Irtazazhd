package com.example.oystercraft

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter for the RecyclerView in CartActivity
class CartAdapter(
    private var cartItems: MutableList<CartItem>, // Mutable list to allow item removal
    private val onRemoveItem: (CartItem) -> Unit // Callback for item removal
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    // ViewHolder class to hold references to the views for each cart item
    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.cartItemImage)
        val itemName: TextView = itemView.findViewById(R.id.cartItemName)
        val itemQuantity: TextView = itemView.findViewById(R.id.cartItemQuantity)
        val itemPrice: TextView = itemView.findViewById(R.id.cartItemPrice)
        val removeButton: ImageButton = itemView.findViewById(R.id.cartItemRemoveButton)
    }

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    // Called to bind data to the views in a ViewHolder
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]

        holder.itemImage.setImageResource(currentItem.imageResId)
        holder.itemName.text = currentItem.name
        holder.itemQuantity.text = "Quantity: ${currentItem.quantity}"
        // Format price to AED currency
        holder.itemPrice.text = String.format("AED %,.2f", currentItem.quantity * currentItem.pricePerUnit)

        // Set click listener for the remove button
        holder.removeButton.setOnClickListener {
            onRemoveItem(currentItem)
        }
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int {
        return cartItems.size
    }

    // Method to update the data in the adapter and refresh the RecyclerView
    fun updateItems(newItems: List<CartItem>) {
        cartItems.clear()
        cartItems.addAll(newItems)
        notifyDataSetChanged() // Notifies the RecyclerView that the data has changed
    }
}