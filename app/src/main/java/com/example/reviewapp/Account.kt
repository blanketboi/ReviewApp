package com.example.reviewapp

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar

class Account : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val delete = findViewById<Button>(R.id.deleteButton)
        val xpBar = findViewById<ProgressBar>(R.id.xpBar)
        xpBar.max = 20

        //TODO: get user xp
        //val currentProgress
        //ObjectAnimator.ofInt(xpBar, "progress", currentProgress)
    }

    fun deleteUserButton() {

        startActivity(Intent(this, LogIn::class.java).apply{})
    }
}