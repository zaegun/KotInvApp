package com.example.kotinv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ItemView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_view)

        // Call the action bar and show the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get the passed in position from the main page and setup the UI
        val position = intent.getIntExtra("position", 0)

        // Get the header, inventory amount, and the note objects
        val invAmt = findViewById<TextView>(R.id.itemAmt)
        val invNote = findViewById<TextView>(R.id.invNote)

        // Setup the interface
        setInterface(position, invAmt, invNote)

        // Get Buttons
        val subButton = findViewById<Button>(R.id.subBtn)
        val addButton = findViewById<Button>(R.id.addBtn)

        // Setup Onclick listeners
        subButton.setOnClickListener {
            // Subtracts 1 to the inventory item
            Global.changeInv(Global.invList[position].invItem, -1)
            //Refreshes the view
            setAmt(position, invAmt)
        }
        addButton.setOnClickListener {
            //Adds 1 to the inventory item
            Global.changeInv(Global.invList[position].invItem, 1)
            //Refreshes the view
            setAmt(position, invAmt)
        }

    }

    // Enables the back button on the page
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun setInterface(position: Int, invAmt: TextView, invNote: TextView) {
        // Set the objects to their respective data values
        title = Global.invList[position].invItem
        invAmt.text = Global.invList[position].invQty.toString()
        invNote.text = Global.invList[position].invNote
    }

    private fun setAmt(position: Int, invAmt: TextView) {
        // Sets the amount in the text box
        invAmt.text = Global.invList[position].invQty.toString()

        // creates the item to update the db
        val item = Global.invList[position]

        // Save data to db and update
        var db = DataBaseHandler(this, null)
        var status = db.updateItem(item)

        // Get the name of the item
        val itemName = Global.invList[position].invItem

        // Show Toast Message based on if it was saved or not
        if (status > -1) {
            Toast.makeText(this, "$itemName updated", Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(this, "$itemName not updated", Toast.LENGTH_LONG).show()
        }

    }
}
