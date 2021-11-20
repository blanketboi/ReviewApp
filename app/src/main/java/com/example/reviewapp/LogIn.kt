package com.example.reviewapp

import android.content.Context
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
            //UserModel(-1, username, password, 0)
            var user = UserModel(username, password, 0)
            var db = DataBaseHandler(this)
            db.insertData(user)
        } catch (e: Exception) {
            Toast.makeText(this, "Error Creating User", Toast.LENGTH_LONG).show()
        }
        //DataBaseHelper(this, "user.db", null, 1)
    }

    fun login(username: String, password: String) {
        if (emptyLogin(username, password)) {
            return
        }


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

    fun userCheck(username : String): Boolean {
        var helper = DataBaseHandler(applicationContext)
        var db = helper.readableDatabase
        var query = db.rawQuery("Select * from " + USER_DATABASE + " where " + COLUMN_ID + " = " + username, null)
        if (query == query.isNull()) {
            return true
        } else {
            return false
        }
    }

    private fun guestLogin() {

    }

}
