package com.example.kotinv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// This adapter class take the data and displays the it in a list in the recycler view

class InvAdapter(
    val list : List<InvItem>
) : RecyclerView.Adapter<InvAdapter.MyViewHolder>(){


    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        // Get the objects in the view for each row
        val invItem : TextView = itemView.findViewById(R.id.invName)
        val invQty : TextView = itemView.findViewById(R.id.invAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // This creates a view of the data based on item_layout.xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        // Send the view to the inner class above
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Populate the field with the the name and amount of the particular item
        // Gets the particular item within the list
        val item = list[position]

        // Apply data
        holder.apply {
            invItem.text = item.invItem
            invQty.text = item.invQty.toString()
        }
    }

    override fun getItemCount(): Int {
        // Gets the total amount of items in the list
        return list.size
    }
}