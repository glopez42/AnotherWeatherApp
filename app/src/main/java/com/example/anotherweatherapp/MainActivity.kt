package com.example.anotherweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Temporal array of cities
        var cities = arrayListOf<String>("Valdemoro", "Ibiza", "Titulcia", "Madrid",)

        setContentView(R.layout.no_cities)
        //Show a message if there is not a city selected
        var noCityMessage: TextView = findViewById<TextView>(R.id.no_city_text)
        if (cities.size == 0){
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

}