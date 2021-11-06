package com.example.anotherweatherapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SearchActivity : AppCompatActivity() {

    var dbHelper: DataBaseHelper = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_city)

        var searchButton: Button = findViewById<Button>(R.id.button_search)
        searchButton.setOnClickListener {
            var cityName: TextView = findViewById<TextView>(R.id.enter_city_edit_text)
            addCity(cityName.text.toString())

        }


    }

    private fun addCity(name: String): Boolean {
        var done = false

        // Gets the data repository in write mode
        val db = dbHelper.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(DataBaseHelper.bbdd.baseObject.CITY_NAME, name)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(DataBaseHelper.bbdd.baseObject.TABLE_NAME, null, values)

        return done
    }
}