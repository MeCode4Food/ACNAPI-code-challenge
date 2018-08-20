package com.example.my.whyislearningandroidsohard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_acitivity3.*

class Activity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acitivity3)

        val text1: String = intent.getStringExtra("arg1")
        val text2: String = intent.getStringExtra("arg2")

        println("$text1 and $text2")
        textView11.text = text1
        textView22.text = text2

        buttonGoBack.setOnClickListener({ _ -> goBackActivity()})
    }

    private fun goBackActivity() {

        finish()
    }


}
