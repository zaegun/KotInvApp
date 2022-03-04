package com.example.kotinv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ItemView : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_view)

        // Get the passed in position from the main page and setup the UI
        val position = intent.getIntExtra("position", 0)

        // Get the header, inventory amount, and the note objects
        val invHeader = findViewById<TextView>(R.id.itemHeader)
        val invAmt = findViewById<TextView>(R.id.itemAmt)
        val invNote = findViewById<TextView>(R.id.invNote)

        // Setup the interface
        setInterface(position, invHeader, invAmt, invNote)

        // Get Buttons
        val subButton = findViewById<Button>(R.id.subBtn)
        val addButton = findViewById<Button>(R.id.addBtn)

        // Setup Onclick listeners
        subButton.setOnClickListener {
            // Subtracts 1 to the inventory item
            Global.changeInv(Global.invList[position].invItem, -1)
            //Refreshes the view
            setInterface(position, invHeader, invAmt, invNote)
        }
        addButton.setOnClickListener {
            //Adds 1 to the inventory item
            Global.changeInv(Global.invList[position].invItem, 1)
            //Refreshes the view
            setInterface(position, invHeader, invAmt, invNote)
        }

    }

    private fun setInterface(position: Int, invHeader: TextView, invAmt: TextView, invNote: TextView) {
        // Set the objects to their respective data values
        invHeader.text = Global.invList[position].invItem
        invAmt.text = Global.invList[position].invQty.toString()
        invNote.text = Global.invList[position].invNote
    }
}
