package com.example.anotherweatherapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anotherweatherapp.DataBaseHelper.bbdd.baseObject.CITY_NAME
import com.example.anotherweatherapp.DataBaseHelper.bbdd.baseObject.TABLE_NAME
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var dbHelper: DataBaseHelper = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var cities: ArrayList<String> = getCities()


        //search city button

        setContentView(R.layout.add_city)
        var searchButton: Button = findViewById<Button>(R.id.button_search)
        searchButton.setOnClickListener {
            var cityName: TextView = findViewById<TextView>(R.id.enter_city_edit_text)
            addCity(cityName.text.toString())

        }

        //floating button action when clicked
        setContentView(R.layout.floating_button_layout)
        var addCityButton: FloatingActionButton = findViewById<FloatingActionButton>(R.id.fab)
        addCityButton.setOnClickListener {
            setContentView(R.layout.add_city)
        }

        setContentView(R.layout.no_cities)

        //Show a message if there is not a city selected
        var noCityMessage: TextView = findViewById<TextView>(R.id.no_city_text)
        if (cities.size == 0) {
            noCityMessage.setText(R.string.no_city)
        } else {

            noCityMessage.visibility = View.INVISIBLE
            setContentView(R.layout.saved_cities)

            val recyclerView = findViewById<RecyclerView>(R.id.rec_View)
            val myAdapter = MyAdapter(this, cities)
            recyclerView.adapter = myAdapter
            recyclerView.layoutManager = LinearLayoutManager(this)

        }

    }

    fun getCities(): ArrayList<String> {
        var cities = arrayListOf<String>()
        var dbRead = dbHelper.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(CITY_NAME)
        val cursor = dbRead.query(
            TABLE_NAME,         // The table to query
            projection,         // The array of columns to return (pass null to get all)
            null,       // The columns for the WHERE clause
            null,    // The values for the WHERE clause
            null,       // don't group the rows
            null,        // don't filter by row groups
            null        // The sort order
        )

        with(cursor) {
            while (moveToNext()) {
                val itemId = getString(getColumnIndexOrThrow(BaseColumns._ID))
                cities.add(itemId)
            }
        }
        return cities
    }

    fun addCity(name: String): Boolean {
        var done = false

        // Gets the data repository in write mode
        val db = dbHelper.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(CITY_NAME, name)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(TABLE_NAME, null, values)

        return done
    }

}