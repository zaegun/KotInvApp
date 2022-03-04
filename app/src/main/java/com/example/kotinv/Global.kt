package com.example.kotinv

object Global {
    var invList = mutableListOf<InvItem>()
    var invListName = mutableListOf<String>()

    fun setList(data : InvItem){
        invList.add(data)
        setListNameArr()
    }

    fun setListNameArr() {
        invListName.clear()
        for(item in invList){
            invListName.add(item.invItem)
        }
    }
}