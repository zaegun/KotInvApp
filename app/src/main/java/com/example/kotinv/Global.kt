package com.example.kotinv

object Global {
    // Global data

    // Holds the object list
    var invList = mutableListOf<InvItem>()

    fun setList(data : InvItem){
        // Adds the data to the inventory list
        invList.add(data)
    }

    fun changeInv(refItem : String, amount : Int){
        // Get the data object
        val foundItem = invList.find {it.invItem == refItem}
        // Add or subtract the amount
        val currAmt = foundItem?.invQty
        val newAmt = currAmt!! + amount
        foundItem.invQty = newAmt
    }

    fun clearInv() {
        invList.clear()
    }

}