package com.example.reviewapp

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val create = findViewById<Button>(R.id.create)
        val login = findViewById<Button>(R.id.login)
        val guest = findViewById<Button>(R.id.guest)
        val username = findViewById<EditText>(R.id.username).toString()
        val password = findViewById<EditText>(R.id.password).toString()

        create.setOnClickListener { createUser(username, password) }
        login.setOnClickListener { login(username, password) }
        guest.setOnClickListener { guestLogin() }

    }

    private fun createUser(username: String, password: String) {
        try {
            var user = UserModel(username, password, 0)
            var db = DataBaseHandler(this)
            db.insertData(user)
            //TODO: set active user & go to main menu
        } catch (e: Exception) {
            Toast.makeText(this, "Error Creating User", Toast.LENGTH_LONG).show()
        }
    }

    fun login(username: String, password: String) {
        if (emptyLogin(username, password)) {
            return
        } //TODO: set active user & go to main menu

    }

    private fun emptyLogin(username: String, password: String) : Boolean {
        if(username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_LONG).show()
            return true
        } else if(password.length < 6) {
            Toast.makeText(this, "Password cannot be less than 6 characters", Toast.LENGTH_LONG).show()
            return true
        } else {
            return false
        }
    }

    private fun guestLogin() {
        val intent = startActivity(Intent(this, MainActivity::class.java))
        startActivity(intent)
        //TODO: go to main menu
    }

}
