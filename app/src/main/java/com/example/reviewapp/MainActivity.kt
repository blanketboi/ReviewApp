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
        var user = findViewById<TextView>(R.id.user)
        val menu = findViewById<ImageButton>(R.id.menu)

        val drawerLayout : DrawerLayout = findViewById(R.id.menu)
        val navView : NavigationView = findViewById(R.id.navView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        if (logedIn) {
            user.text = username
        } else {
            user.text = "Guest"
        }

    }

//    fun setUser(username : String, logedIn : Boolean) {
//        if (logedIn) {
//            user.text = username
//        } else {
//            user.text = "Guest"
//        }
//    }

    //TODO: get active username

}