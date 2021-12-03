package com.example.reviewapp

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.HeaderViewListAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    val username = intent.getStringExtra("username").toString()
    val logedIn = intent.getStringExtra("logedIn").toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = findViewById<TextView>(R.id.user)
        val menu = findViewById<ImageButton>(R.id.menu)

        menu.setOnClickListener{menuCreate()}

        if (logedIn == "true") {
            user.text = username
        } else {
            user.text = "Guest"
        }
    }

    fun menuCreate() {
        val navView : NavigationView = findViewById(R.id.navView)
        val headerView : View = navView.getHeaderView(0)
        val navUser : TextView = headerView.findViewById(R.id.navHeaderName)
        val account = navView.findViewById<Button>(R.id.account)
        val log : TextView = navView.findViewById(R.id.login)

        if (logedIn == "true") {
            navUser.text = username
            log.text = getString(R.string.navLogout)
        } else {
            navUser.text = getString(R.string.guest)
            log.text = getString(R.string.navLogin)
        }
        navView.isShown
    }

    //TODO: get active username
    //TODO: call top 4 restaurants in recyclerView

}