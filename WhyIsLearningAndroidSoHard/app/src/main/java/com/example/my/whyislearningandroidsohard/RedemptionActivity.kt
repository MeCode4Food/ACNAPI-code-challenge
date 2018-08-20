package com.example.my.whyislearningandroidsohard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_redemption.*
import org.json.JSONObject

class RedemptionActivity : AppCompatActivity() {
    private lateinit var url:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redemption)
        url = "https://acnapi-code-challenge.herokuapp.com"

        textView1_1.text= "Red Umbrella"
        textView1_2.text= "Yours for 200 points"
        textView2_1.text= "Beats by Dre coupons at 70% OFF!"
        textView2_2.text= "Get yours now!"
        textView3_1.text= "Buy 2 get 1 free!"
        textView3_2.text= "At Factory Outlet Store. While stocks last."

        button1.setOnClickListener({ _ -> redeemPoints()})

        // TODO
        var jsonObj = JSONObject()
        try {
            var data = JSONObject()
            data.put("points", 0)
            jsonObj.put("data", data)
            val mRequestBody = jsonObj.toString()
        }
        catch (e: Exception){
            println(e.toString())
        }

        val addUser = JsonObjectRequest(
                Request.Method.POST,
                url + "/api/user",
                jsonObj ,
                Response.Listener<JSONObject> { response ->
                    println(response)
                },
                Response.ErrorListener { response ->
                    println("error")
                    println(response)
                }
        )
    }

    private fun redeemPoints() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
