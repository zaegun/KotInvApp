package com.example.kotinv

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DataBaseHandler(var context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        private val DATABASE_NAME = "KotInvDB"
        val TABLE_NAME = "InvItem"
        val COL_NAME = "name"
        val COL_QTY = "qty"
        val COL_MEMO = "memo"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        // Create the SQL table
        val createTable = ("CREATE TABLE " + TABLE_NAME + " (" +
                COL_NAME + " TEXT PRIMARY KEY," +
                COL_QTY + " INTEGER," +
                COL_MEMO + " TEXT" + ")")

        // Execute calling sqlite
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        // This checks if the table already exists
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(item : InvItem) : Long {
        // Function is used to insert data into the SQL table
        // Get the database and the initiate the values
        val db = this.writableDatabase
        var cv = ContentValues()

        // Populate the values
        cv.put(COL_NAME, item.invItem)
        cv.put(COL_QTY, item.invQty)
        cv.put(COL_MEMO, item.invNote)

        // Inert values into the db
        val success = db.insert(TABLE_NAME, null, cv)
        db.close()
        return success
    }

    fun readData() : ArrayList<InvItem> {
        // Get a list of all items
        val invList : ArrayList<InvItem> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME"

        // readable variable of the database
        val db = this.readableDatabase

        // Get the cursor
        val cursor : Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        // Store the values we will be using
        var name : String
        var qty : Int
        var memo : String

        // Get the values and apply it to the list
        if (cursor!!.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME))
                qty = cursor.getInt(cursor.getColumnIndexOrThrow(COL_QTY))
                memo = cursor.getString(cursor.getColumnIndexOrThrow(COL_MEMO))

                val item = InvItem(name, qty, memo)
                invList.add(item)
            } while (cursor.moveToNext())
        }

        // return the list
        return invList
    }

    fun updateItem(item : InvItem) : Int {
        // Get the database
        val db = this.writableDatabase
        var cv = ContentValues()
        var invName = item.invItem

        // Populate the values
        cv.put(COL_NAME, item.invItem)
        cv.put(COL_QTY, item.invQty)
        cv.put(COL_MEMO, item.invNote)

        // Update the item in the db and report the success or not
        val success = db.update(TABLE_NAME, cv,"$COL_NAME=?", arrayOf(invName))
        db.close()
        return success

    }

    fun deleteItem(item : InvItem) : Int {
        // Get the database
        val db = this.writableDatabase
        var cv = ContentValues()
        var invName = item.invItem

        // Populate the values
        cv.put(COL_NAME, item.invItem)

        // Delete the item
        val success = db.delete(TABLE_NAME, "$COL_NAME=?", arrayOf(invName))
        db.close()
        return success
    }


}