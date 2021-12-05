package com.example.reviewapp

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class Account : AppCompatActivity() {

    val id = intent.getIntExtra("id", 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val nameTitle = findViewById<TextView>(R.id.title)
        val delete = findViewById<Button>(R.id.deleteButton)
        val xpBar = findViewById<ProgressBar>(R.id.xpBar)

        nameTitle.text = intent.getStringExtra("username")

        delete.setOnClickListener { deleteUserButton(id) }
        xpBar.max = 25

        val userXP = DataBaseHandler(this).getUserXP(id)
        ObjectAnimator.ofInt(xpBar, "progress", userXP)
    }

    fun deleteUserButton(id : Int) {
        DataBaseHandler(this).deleteUser(id)
        startActivity(Intent(this, LogIn::class.java))
    }
}