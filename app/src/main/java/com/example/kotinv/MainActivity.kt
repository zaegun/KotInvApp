package com.example.kotinv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Kotlin Inventory"

        loadData()
        setList()

        val addButton = findViewById<Button>(R.id.btnAdd)
        addButton.setOnClickListener{
            var textBox = findViewById<EditText>(R.id.editText)
            var enteredText = textBox.text.toString()
            val invItem = InvItem(enteredText, 0, "")
            Global.setList(invItem)
            textBox.setText("")
            setList()
        }

        }

    private fun setList() {
        var listView = findViewById<ListView>(R.id.listView)

        val arrayAdapter : ArrayAdapter<*>
        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            Global.invListName)
        listView.adapter = arrayAdapter
    }

    private fun loadData() {
        var wordList = mutableListOf<String>()
        wordList.add("Apple")
        wordList.add("Pear")
        wordList.add("Tomato")

        for (i in wordList.indices) {
            val invItem = InvItem(wordList[i], 0, "")
            Global.setList(invItem)
        }
    }

    }
