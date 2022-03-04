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

        val arrayAdapter : ArrayAdapter<*>

        var wordList = mutableListOf<String>()
        wordList.add("apple")
        wordList.add("pear")
        wordList.add("Tomato")

        for (i in wordList.indices) {
            val invItem = Item(wordList[i], 0, "")
            Global.setList(invItem)
        }
        Global.setListNameArr()

        var listView = findViewById<ListView>(R.id.listView)
        var textView = findViewById<TextView>(R.id.editText)
        textView.text = Global.invListName.toString()


        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            Global.invListName)
        listView.adapter = arrayAdapter
        }

    }

class Item (itemName : String, itemQty : Int, itemNote : String){
    var invItem = ""
    var invQty = 0
    var invNote = ""

    init {
        invItem = itemName
        invQty = itemQty
        invNote = itemNote
    }

}

object Global {
    var invList = mutableListOf<Item>()
    var invListName = mutableListOf<String>()

    fun setList(data : Item){
        invList.add(data)
    }

    fun setListNameArr() {
        invListName.clear()
        for(item in invList){
            invListName.add(item.invItem)
        }
    }
}