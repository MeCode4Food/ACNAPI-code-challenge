package com.example.my.whyislearningandroidsohard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mToggle: ActionBarDrawerToggle
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.nav_action_header)
        mToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        buttonNext.setOnClickListener({ _ -> openActivity2()})

        setSupportActionBar(nav_action_header as Toolbar)

        drawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        menu_view.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(mToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return when(id){
            R.id.home -> consume{ navigateToHome() }
            R.id.settings -> consume{ navigateToSettings() }
            R.id.account -> consume{ navigateToAccount() }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToHome() {
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }

    private fun navigateToSettings() {
        val settingsIntent = Intent(this, Activity2::class.java)
        startActivity(settingsIntent)
    }

    private fun navigateToAccount() {
        val accountIntent = Intent(this, FacebookLoginActivity::class.java)
        startActivity(accountIntent)
    }

    private fun openActivity2() {
        val intent = Intent(this, Activity2::class.java)
        startActivity(intent)
    }

    inline fun consume(f: () -> Unit ):Boolean{
        f()
        return true
    }
}

