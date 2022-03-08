package com.example.kotinv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity(), InvAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Kotlin Inventory"

        // Load and set the initial List
        loadData()
        setList()

        // Get the button and listen to see if it's pressed
        val addButton = findViewById<Button>(R.id.btnAdd)
        addButton.setOnClickListener{
            // When pressed it will take the text and add it to the list
            openDialog()
        }

    }

    override fun onItemClick(position: Int) {
        // Open the clicked item
        startItemActivity(position)
    }

    override fun onResume() {
        super.onResume()
        // Refresh the list when the Activity is resumed
        setList()
    }

    private fun loadData() {
        // Load data into the Global list
    }

    private fun setList() {
        // Get the recycler list view
        val listView = findViewById<RecyclerView>(R.id.listView)

        // Use the adapter to apply the global list of data to the layout
        listView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = InvAdapter(Global.invList, this@MainActivity)

        }
    }

    private fun openDialog() {
        // Sets the dialog box
        val dialog = BottomSheetDialog(this)

        // Show the dialog box
        val view = layoutInflater.inflate(R.layout.bottom_sheet, null)

        // Get objects in the dialog box
        val itemName = view.findViewById<EditText>(R.id.footItemName)
        val itemMemo = view.findViewById<EditText>(R.id.footMemo)
        val itemSub = view.findViewById<Button>(R.id.footSubBtn)
        val itemAmt = view.findViewById<TextView>(R.id.footAmt)
        val itemAdd = view.findViewById<Button>(R.id.footAddBtn)

        // Sets listeners on the + and - buttons
        itemSub.setOnClickListener() {
            // Subtract the amount by 1
            adjustAmtText(itemAmt, -1)
        }

        itemAdd.setOnClickListener() {
            // Add to the amount by 1
            adjustAmtText(itemAmt, 1)
        }

        // Gets the add item button
        val btnAdd = view.findViewById<Button>(R.id.bttmAddBtn)
        btnAdd.setOnClickListener(){
            // Get all the inputted text
            val enteredText = itemName.text.toString()
            val enteredMemo = itemMemo.text.toString()
            val enteredAmt = itemAmt.text.toString().toInt()

            // Create the inventory object
            val invItem = InvItem(enteredText, enteredAmt, enteredMemo)

            // Add it to the data
            Global.setList(invItem)

            // Reset the EditText object
            itemName.setText("")
            itemMemo.setText("")
            itemAmt.text = "0"

            // Refreshes the recycler view
            setList()

            // Dismiss Dialog
            dialog.dismiss()
        }

        // Set the view dialog and show it
        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()

    }

    fun adjustAmtText(textView : TextView, amt : Int) {
        // Get the amount in the textview and add the passed through amount
        val curAmt = textView.text.toString().toInt()
        val newAmt = curAmt + amt

        // Change the textview text to the new amount
        textView.text = newAmt.toString()
    }

    private fun startItemActivity (position : Int) {
        // Create the intent for the ItemView Activity
        val intent = Intent(this@MainActivity, ItemView::class.java)

        // Pass along the position data
        intent.putExtra("position", position)

        // Start the Activity
        startActivity(intent)
    }
}
