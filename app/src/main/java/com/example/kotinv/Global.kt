package com.example.kotinv

object Global {
    // Global data

    // Holds the object list
    var invList = mutableListOf<InvItem>()


    fun setList(data : InvItem){
        // Adds the data to the inventory list
        invList.add(data)
    }

}