package com.example.reviewapp

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar

class Account : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val xpBar = findViewById<ProgressBar>(R.id.xpBar)
        xpBar.max = 20

        val currentProgress =

        ObjectAnimator.ofInt(xpBar, "progress", )
    }
}