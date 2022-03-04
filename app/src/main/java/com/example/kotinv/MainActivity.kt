package com.example.kotinv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Kotlin Inventory"

        // Load and set the initial List
        loadData()
        var listView = findViewById<RecyclerView>(R.id.listView)
        listView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = InvAdapter(Global.invList)

        }


        // Get the button and listen to see if it's pressed
        val addButton = findViewById<Button>(R.id.btnAdd)
        addButton.setOnClickListener{
            // When pressed it will take the text and add it to the list
            addItem()
        }

    }

    private fun loadData() {
        // PLACEHOLDER
        Global.setList(InvItem("Apple", 1, ""))
        Global.setList(InvItem("Tomato", 3, ""))
    }

    private fun setList() {
        // Get the ListView
        val listView = findViewById<ListView>(R.id.listView)

        // Create an Array Adapter to display the list
        val arrayAdapter : ArrayAdapter<*>
        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            Global.invListName)

        // Use Adapter to populate the list with the Global data
        listView.adapter = arrayAdapter
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

        // Refreshes the list
        setList()
    }



}
