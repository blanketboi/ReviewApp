package com.example.reviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var toggle : ActionBarDrawerToggle
        val user = findViewById<TextView>(R.id.user)
        val menu = findViewById<ImageButton>(R.id.menu)
        val navUser = findViewById<TextView>(R.id.navHeaderName)
        val log = findViewById<TextView>(R.id.login)
        //val drawerLayout : DrawerLayout = findViewById(R.id.menu)
        val navView : NavigationView = findViewById(R.id.navView)

       // menu.setOnClickListener{drawerLayout}
/*
        val username = intent.getStringExtra("username").toString()
        val logedIn = intent.getStringExtra("logedIn").toString()

        if (logedIn == "true") {
            user.text = username
            navUser.text = username
            log.text = "Logout"
        } else {
            user.text = "Guest"
            navUser.text = "Guest"
            log.text = "Login"
        }
*/
    }

    //TODO: get active username
    //TODO: call top 4 restaurants in recyclerView

}