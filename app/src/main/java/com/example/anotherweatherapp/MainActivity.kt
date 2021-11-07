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
import android.content.Intent




class MainActivity : AppCompatActivity() {

    var dbHelper: DataBaseHelper = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var cities: ArrayList<String> = getCities()

        //floating button action when clicked
        setContentView(R.layout.no_cities)
        setAddButton()

        //Show a message if there is not a city selected
        var noCityMessage: TextView = findViewById<TextView>(R.id.no_city_text)
        if (cities.size == 0) {
            noCityMessage.setText(R.string.no_city)
        } else {

            noCityMessage.visibility = View.INVISIBLE
            setContentView(R.layout.saved_cities)
            setAddButton()

            val recyclerView = findViewById<RecyclerView>(R.id.rec_View)
            val myAdapter = MyAdapter(this, cities)
            recyclerView.adapter = myAdapter
            recyclerView.layoutManager = LinearLayoutManager(this)

        }

    }

    private fun getCities(): ArrayList<String> {
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
                val itemId = getString(getColumnIndexOrThrow(CITY_NAME))
                cities.add(itemId)
            }
        }
        return cities
    }

    private fun setAddButton(){
        val addCityButton: FloatingActionButton = findViewById<FloatingActionButton>(R.id.fab)
        addCityButton.setOnClickListener {
            val myIntent = Intent(this, SearchActivity::class.java)
            //myIntent.putExtra("db", value)
            startActivity(myIntent)
        }
    }

}