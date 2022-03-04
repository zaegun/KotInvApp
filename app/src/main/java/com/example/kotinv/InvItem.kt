package com.example.kotinv

class InvItem(itemName : String, itemQty : Int, itemNote : String) {
        var invItem = ""
        var invQty = 0
        var invNote = ""

        init {
            invItem = itemName
            invQty = itemQty
            invNote = itemNote
        }
}