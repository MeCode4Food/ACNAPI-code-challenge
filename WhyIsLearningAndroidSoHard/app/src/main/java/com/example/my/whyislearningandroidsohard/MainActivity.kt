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
            R.id.promotion -> consume{ navigateToPromotions() }
            R.id.store_locator -> consume{ navigateToStoreLocator() }
            R.id.redemption -> consume{ navigateToRedemption() }
            R.id.parking -> consume{ navigateToParking() }
            R.id.settings -> consume{ navigateToSettings() }
            R.id.account -> consume{ navigateToAccount() }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToRedemption() {
        val redemptionIntent = Intent(this, RedemptionActivity::class.java)
        startActivity(redemptionIntent)
    }

    private fun navigateToParking() {
        val parkingIntent = Intent(this, ParkingActivity::class.java)
        startActivity(parkingIntent)
    }

    private fun navigateToStoreLocator() {
        val storeLocatorIntent = Intent(this, StoreLocatorActivity::class.java)
        startActivity(storeLocatorIntent)
    }

    private fun navigateToPromotions() {
        val promotionIntent = Intent(this, PromotionsActivity::class.java)
        startActivity(promotionIntent)
    }

    private fun navigateToHome() {
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }

    private fun navigateToSettings() {

    }

    private fun navigateToAccount() {
        val accountIntent = Intent(this, FacebookLoginActivity::class.java)
        startActivity(accountIntent)
    }

    inline fun consume(f: () -> Unit ):Boolean{
        f()
        return true
    }
}

