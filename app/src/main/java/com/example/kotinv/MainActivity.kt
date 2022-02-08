package com.example.kotinv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button : Button = findViewById<Button>(R.id.btnOpenAddInv)

        button.setOnClickListener {
            val intent = Intent(this@MainActivity, AddInv::class.java)
            startActivity(intent)
            finish()
        }
    }
}