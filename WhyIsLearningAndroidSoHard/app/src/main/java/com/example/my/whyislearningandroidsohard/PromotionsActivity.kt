package com.example.my.whyislearningandroidsohard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_promotions.*

class PromotionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotions)

        textView1_1.text= "Addidas Sweater at 20% OFF!"
        textView1_2.text= "Valid for purchases in Skyview only"
        textView2_1.text= "Beats by Dre at 10% OFF!"
        textView2_2.text= "When RM150 of purchase is made in NIKE"
        textView3_1.text= "Buy 2 get 1 free!"
        textView3_2.text= "At Factory Outlet Store. While stocks last."
        textView4_1.text= "Uniqlo Summer Collection New Arrivals"
        textView4_2.text= "From 22nd June to 21st August"
        textView5_1.text= "Addidas shoe collection clearance!"
        textView5_2.text= "While stocks last"
        textView6_1.text= "Lunch Promotion at Newdles!"
        textView6_2.text= "Amazing noodles with incredible value"
    }
}
