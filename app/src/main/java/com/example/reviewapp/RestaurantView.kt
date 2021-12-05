package com.example.reviewapp

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class RestaurantView : AppCompatActivity() {

    val restID = intent.getIntExtra("restID", 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_view)

        val displayTitle = findViewById<TextView>(R.id.restTitle)
        val displayDescription = findViewById<TextView>(R.id.restDescription)
        val displayCuisine = findViewById<TextView>(R.id.restCuisine)
        val displayImage = findViewById<ImageView>(R.id.restImage)      //TODO: implement image sql support
        val deliversYes = findViewById<ImageView>(R.id.deliversYes)
        val deliversNo = findViewById<ImageView>(R.id.deliversNo)
        val eatYes = findViewById<ImageView>(R.id.eatYes)
        val eatNo = findViewById<ImageView>(R.id.eatNo)
        val restReviewButton = findViewById<ImageButton>(R.id.restReviewButton)

        val db = DataBaseHandler(this)
        val restaurant = db.getRestaurant(restID)
        val title = restaurant.title
        val description = restaurant.description
        val cuisine = restaurant.cuisine
        val eatIn = restaurant.eatIn
        val delivery = restaurant.delivery

        displayTitle.text = title
        displayDescription.text = description
        displayCuisine.text = cuisine

        if (eatIn == 0) {
            eatYes.setVisibility(View.GONE)
            eatNo.setVisibility(View.VISIBLE)
        } else if (eatIn == 1) {
            eatYes.setVisibility(View.VISIBLE)
            eatNo.setVisibility(View.GONE)
        }

        if (delivery == 0) {
            deliversYes.setVisibility(View.GONE)
            deliversNo.setVisibility(View.VISIBLE)
        } else if (delivery == 1) {
            deliversYes.setVisibility(View.VISIBLE)
            deliversNo.setVisibility(View.GONE)
        }

        restReviewButton.setOnClickListener {startActivity(Intent(this, WriteReview::class.java).apply { putExtra("restID", restID) })}
    }


}