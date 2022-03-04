package com.example.kotinv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
            addItem()
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
        // PLACEHOLDER DATA
        Global.setList(InvItem("Apple", 1, ""))
        Global.setList(InvItem("Tomato", 3, ""))
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

    private fun addItem() {
        // Get the EditText object
        val textBox = findViewById<EditText>(R.id.editText)

        // Convert the text to a string
        val enteredText = textBox.text.toString()

        // Create the inventory object
        val invItem = InvItem(enteredText, 0, "")

        // Add it to the data
        Global.setList(invItem)

        // Reset the EditText object
        textBox.setText("")

        // Refreshes the recycler view
        setList()

        // Open the item screen with the item created
        val newPos = Global.invList.size - 1
        startItemActivity(newPos)

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
