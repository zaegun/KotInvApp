package com.example.kotinv

class InvItem(itemName : String, itemQty : Int, itemNote : String) {
    // This class creates an object that has the inventory name, quantity, and any notes
        var invItem = ""
        var invQty = 0
        var invNote = ""
        var id : Int = 0

        init {
            invItem = itemName
            invQty = itemQty
            invNote = itemNote
        }
}