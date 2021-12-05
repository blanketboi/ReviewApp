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

    val userID = intent.getIntExtra("userID", 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_review)

        val restaurantName = intent.getStringExtra("restaurantName").toString()

        val title = findViewById<TextInputEditText>(R.id.reviewTitle).toString()
        val description = findViewById<TextInputEditText>(R.id.reviewDescriptionText).toString()
        val rating = findViewById<RatingBar>(R.id.ratingBar).rating.toInt()
        val restName = findViewById<TextView>(R.id.restName)
        val imageInput = findViewById<Button>(R.id.uploadImage)
        val submitButton = findViewById<Button>(R.id.submitReview)

        imageInput.setOnClickListener {  }
        submitButton.setOnClickListener { createRating(rating, title, description) }
        restName.text = restaurantName

    }

    //TODO: implement image upload

    fun createRating(rating : Int, title : String, description : String) {
        val database = DataBaseHandler(this)
        val restID = intent.getIntExtra("restID", 0)
        var userXP = database.getUserXP(userID)
        if (title.isNotBlank()) {
            userXP += 1
        }
        if (description.isNotBlank()) {
            userXP += 2
        }
        try {
            database.setUserXP(userID, userXP)
            val review = RatingModel(id = 0, userID, restID, rating, title, description)
            database.insertRating(review)
        } catch (e : Exception) {

        }
    }
}