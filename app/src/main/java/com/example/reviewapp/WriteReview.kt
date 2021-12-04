package com.example.reviewapp

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import java.lang.Exception

class WriteReview : AppCompatActivity() {

    val userID = intent.getStringExtra("userID")?.toInt()
    val restaurantID = intent.getStringExtra("restaurantID")?.toInt()
    val restaurantName = intent.getStringExtra("restaurantName").toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_review)

        val title = findViewById<TextInputEditText>(R.id.reviewTitle).toString()
        val description = findViewById<TextInputEditText>(R.id.reviewDescriptionText).toString()
        val rating = findViewById<RatingBar>(R.id.ratingBar)
        val restName = findViewById<TextView>(R.id.restName)
        val imageInput = findViewById<Button>(R.id.uploadImage)
        val submitButton = findViewById<Button>(R.id.submitReview)

        imageInput.setOnClickListener {  }
        submitButton.setOnClickListener { createRating(userID, restaurantID, rating, title, description) }

        restName.text = restaurantName
    }
    /*
    TODO:
     fix rating
     implement image upload
     */
    fun createRating(userID : Int, restaurantID : Int, rating : Int, title : String, description : String) {
        var tempXP : Int = 0
        if (title.isNotBlank()) {
            tempXP += 1
        }
        if (description.isNotBlank()) {
            tempXP += 2
        }
        try {
            val review = RatingModel(id = 0, userID, restaurantID, rating, title, description)
            val database = DataBaseHandler(this)
            database.insertRating(review)
        } catch (e : Exception) {

        }
    }
}