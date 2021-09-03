package com.example.anotherweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var citySelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Temporal array of cities
        var cities = arrayListOf<String>("Valdemoro", "Ibiza", "Titulcia", "Madrid")


        //Show a message if there is not a city selected
        var noCityMessage: TextView = findViewById<EditText>(R.id.noCityText)
        if (!citySelected){
            noCityMessage.setText(R.string.no_city)
        } else {
            noCityMessage.visibility = View.INVISIBLE
        }




    }

}