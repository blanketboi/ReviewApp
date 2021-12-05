package com.example.reviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.ToggleButton

class RestaurantSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_search)

        val textBar = findViewById<EditText>(R.id.searchBar).toString()
        val eatIn = findViewById<Switch>(R.id.eatIn)
        val delivery = findViewById<Switch>(R.id.delivers)
        val searchButton = findViewById<Button>(R.id.searchButton)

        var eatInInt: Int = 0
        var deliveryInt: Int = 0
        eatIn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                eatInInt = 1
            } else {
                eatInInt = 0
            }
        }
        delivery.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                deliveryInt = 1
            } else {
                deliveryInt = 0
            }
        }
        val searchResult = searchButton.setOnClickListener {DataBaseHandler(this).restaurantSearch(textBar, eatInInt, deliveryInt)}
        //TODO: recyclerView
    }

}