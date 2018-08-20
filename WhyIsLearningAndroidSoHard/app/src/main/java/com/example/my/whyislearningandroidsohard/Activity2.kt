package com.example.my.whyislearningandroidsohard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_acitivity2.*

class Activity2 : AppCompatActivity() {

    private lateinit var mToggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acitivity2)

        buttonActivity2to3.setOnClickListener({ _ -> openActivity3()})

        mToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(mToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openActivity3() {
        val text1: String = editText1.text.toString()
        val text2: String = editText2.text.toString()

        val intent = Intent(this, Activity3::class.java)

        println("text1 $text1 and text2 $text2")
        intent.putExtra("arg1", text1)
        intent.putExtra("arg2", text2)
        startActivity(intent);
    }
}
