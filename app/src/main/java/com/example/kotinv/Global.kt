package com.example.kotinv

object Global {
    // Global data

    // Holds the object list
    private var invList = mutableListOf<InvItem>()

    // Holds the object name in a list
    var invListName = mutableListOf<String>()

    fun setList(data : InvItem){
        // Adds the data to the inventory list
        invList.add(data)
        // Sets the name array for easy listing
        setListNameArr()
    }

    private fun setListNameArr() {
        // Clears the array so it's always lined up with the primary data
        invListName.clear()

        // Creates the name list based off the data
        for(item in invList){
            invListName.add(item.invItem)
        }
    }
}