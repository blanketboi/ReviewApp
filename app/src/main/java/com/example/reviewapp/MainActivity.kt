package com.example.reviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    val username = intent.getStringExtra("username").toString()
    val logedIn = intent.getStringExtra("logedIn").toString()
    val id = intent.getIntExtra("id", 0)
    val navView : NavigationView = findViewById(R.id.navView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = findViewById<TextView>(R.id.user)
        val menu = findViewById<ImageButton>(R.id.menu)
        navView.setNavigationItemSelectedListener { this }  //TODO: fix navigation

        menu.setOnClickListener{menuCreate()}

        if (logedIn == "true") {
            user.text = username
        } else {
            user.text = "Guest"
        }
    }

    fun menuCreate() {
        val headerView : View = navView.getHeaderView(0)
        val navUser : TextView = headerView.findViewById(R.id.navHeaderName)
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

    override fun onNavigationItemSelected(item: MenuItem) : Boolean {
        when(item.itemId) {
            //R.id.navHome -> startActivity(Intent(this, MainActivity::class.java))
            R.id.navList -> startActivity(Intent(this, MainActivity::class.java))   //TODO: create restaurant search Activity
            R.id.account -> startActivity(Intent(this, Account::class.java).apply { putExtra("id", id) })
            R.id.loginOut -> startActivity(Intent(this, LogIn::class.java))
        }
        return true
    }

    //TODO: call 4 restaurants in recyclerView
    //TODO: start Activity with drawer closed and working

}