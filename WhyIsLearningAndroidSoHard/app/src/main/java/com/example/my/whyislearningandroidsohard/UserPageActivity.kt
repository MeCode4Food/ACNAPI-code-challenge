package com.example.my.whyislearningandroidsohard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_page.*
import org.json.JSONObject

class UserPageActivity : AppCompatActivity() {
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var picture: JSONObject
    private lateinit var data: JSONObject


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)

        try {
            user_name_text.text = intent.getStringExtra("name")
            user_email_text.text = intent.getStringExtra("email")
            if(intent.getStringExtra("points").isNullOrEmpty()){
                user_points_text.text = "0"
            }
            else{
                user_points_text.text = intent.getStringExtra("points")
            }
            picture = JSONObject(intent.getStringExtra("picture"))
            var url = picture.getJSONObject("data").getString("url")
            var urlfromid = "graph.facebook.com/" + intent.getStringExtra("id") + "/picture?type=large"
            println(url)

            Picasso.with(this)
                    .load(url)
                    .resize(200, 200)
                    .into(user_image)

        }
        catch (e:Exception){
            println(e.toString())
        }

        button_go_back.setOnClickListener({_ -> backToMain()})
    }

    private fun backToMain() {
        val homeIntent = Intent(this, MainActivity::class.java)

        startActivity(homeIntent)
    }
}
