package com.example.reviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.Exception

class LogIn : AppCompatActivity() {

    val database = DataBaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val create = findViewById<Button>(R.id.create)
        val login = findViewById<Button>(R.id.login)
        val guest = findViewById<Button>(R.id.guest)
        val username = findViewById<EditText>(R.id.username).toString()
        val password = findViewById<EditText>(R.id.password).toString()

        create.setOnClickListener { createUser(username, password) }
        login.setOnClickListener { login(username, password)  }
        guest.setOnClickListener { guestLogin() }

    }

    private fun createUser(username: String, password: String) {
        try {
            val user = UserModel(id = 0, username, password, xp = 0)
            database.insertUser(user)
            //TODO: set active user & go to main menu
            val id = database.getUserID(username, password)
            val logedIn : String = "true"
            val main = Intent(this, MainActivity::class.java).apply {
                putExtra("username", username)
                putExtra("logedIn", logedIn)
                putExtra("id", id)
            }
            startActivity(main)
        } catch (e: Exception) {
            Toast.makeText(this, "Error Creating User", Toast.LENGTH_LONG).show()
        }
    }

    fun login(username: String, password: String) {
            try {
                var userArray = database.userList()
                var i = 0
                for (e in userArray) {
                    if (userArray[i].username == username && userArray[i].password == password) {
                        val username = userArray[i].username
                        val id = database.getUserID(username, password)
                        val logedIn : String = "true"
                        val main = Intent(this, MainActivity::class.java).apply {
                            putExtra("username", username)
                            putExtra("logedIn", logedIn)
                            putExtra("id", id)
                        }
                        startActivity(main)
                    } else {
                        i+=1
                    }
                }
            } catch (e : Exception) {
                Toast.makeText(this, "Incorrect password or user does not exist", Toast.LENGTH_LONG).show()
            }
    }

    private fun emptyLogin(username: String, password: String) : Boolean {
        if(username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_LONG).show()
            return true
        } else if(password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
            return true
        } else {
            return false
        }
    }

    private fun guestLogin() {
        try {
            val logedIn : String = "false"
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("logedIn", logedIn)
            }
            startActivity(intent)
        } catch (e : Exception) {
            Toast.makeText(this, "Error with Guest", Toast.LENGTH_LONG).show()
        }

    }

}
