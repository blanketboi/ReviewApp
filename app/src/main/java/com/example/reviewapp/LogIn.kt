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
import kotlin.math.log

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
            var user = UserModel(id = 0, username, password, xp = 0)
            database.insertData(user)
            //TODO: set active user & go to main menu
        } catch (e: Exception) {
            Toast.makeText(this, "Error Creating User", Toast.LENGTH_LONG).show()
        }
    }

    fun login(username: String, password: String) {

            var userArray = database.userList()
            var i = 0
            for (e in userArray) {
                if (userArray[i].username == username && userArray[i].password == password) {
                    var name = userArray[i].username
                    var logedIn : Boolean = true
                    val main = Intent(this, MainActivity::class.java).apply {
                        putExtra("username", name)
                        putExtra("logedIn", logedIn)
                    }
                    startActivity(main)
                } else {
                    i+=1
                }

        }
    //TODO: set active user & go to main menu

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
        var logedIn : String = "true"
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("username", "Guest")
            putExtra("logedIn", logedIn)
        }
        startActivity(intent)
        //TODO: go to main menu
    }

}
