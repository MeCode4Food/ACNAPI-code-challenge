package com.example.my.whyislearningandroidsohard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_fb_log_in.*
import android.content.Intent
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import com.facebook.*
import org.json.JSONObject


class FacebookLoginActivity : AppCompatActivity() {
    private lateinit var callbackManager: CallbackManager
    private lateinit var loginToken: AccessToken
    private var output = ""
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fb_log_in)
        url = "https://acnapi-code-challenge.herokuapp.com"
        callbackManager = CallbackManager.Factory.create();
        fb_login_button.setReadPermissions("public_profile", "email", "user_birthday")

        fb_login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                loginToken = loginResult.accessToken
                val accessToken = AccessToken.getCurrentAccessToken()

                val userId = loginToken.userId

                login_status.setText("OK")

                val request = GraphRequest.newMeRequest(accessToken){ obj, response->
                    println("in here")
                    println(obj.toString())
                    try{
                        if(obj.has("name")){
                            output += "Name: " + obj.getString("name") + "\n"
                        }
                        if(obj.has("email")){
                            output += "email: " + obj.getString("email") + "\n"
                        }
                        if(obj.has("gender")){
                            output += "gender: " + obj.getString("gender") + "\n"
                        }
                        if(obj.has("picture")){
                        output += "picture: " + obj.getJSONObject("picture").toString() + "\n"
                        }
                    }
                    catch(e: Exception){
                        Toast.makeText(this@FacebookLoginActivity, "Error here! " +e.toString(), Toast.LENGTH_LONG).show()
                    }

                }
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email,gender,birthday,picture.type(large)")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
                login_status.setText("Canceled")
            }

            override fun onError(exception: FacebookException) {

                login_status.setText("Failed")
            }
        })

        update_button.setOnClickListener({_ -> updateTextHere()})

        if((AccessToken.getCurrentAccessToken() == null )|| !AccessToken.getCurrentAccessToken().isExpired()){
            updateTextHere()
        }
    }

    private fun updateTextHere() {
        val accessToken = AccessToken.getCurrentAccessToken()

        login_status.setText("OK")

        val request = GraphRequest.newMeRequest(accessToken){ obj, response->
            println("in here")
            println(obj.toString())
            try{
                val userPageIntent = Intent(this, UserPageActivity::class.java)

                if(obj.has("name")){
                    output += "Name: " + obj.getString("name") + "\n"
                    userPageIntent.putExtra("name", obj.getString("name"))
                }
                if(obj.has("email")){
                    output += "email: " + obj.getString("email") + "\n"
                    userPageIntent.putExtra("email", obj.getString("email"))
                }
                if(obj.has("picture")){
                    output += "picture: " + obj.getJSONObject("picture").toString() + "\n"
                    userPageIntent.putExtra("picture", obj.getString("picture"))
                }
                if(obj.has("id")){
                    userPageIntent.putExtra("id", obj.getString("id"))
                }

                println(output)

                // search database for user. if there exists, retreive points
                val requestQueue = Volley.newRequestQueue(this)
                println("HEREHEREHERE")
                val objectRequest = JsonObjectRequest(
                        Request.Method.GET,
                        url + "/api/user?email=" + obj.getString("email"),
                        null,
                        Response.Listener<JSONObject> { response ->
                            val result = response["status"].toString()
                            if(result == "Resource not found"){

                                var jsonObj = JSONObject()
                                try {
                                    var data = JSONObject()
                                    data.put("name", obj.getString("name"))
                                    data.put("email", obj.getString("email"))
                                    data.put("points", 3000)
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

                                userPageIntent.putExtra("points", 3000)
                            }
                            else{
                                userPageIntent.putExtra("points", response.getJSONObject("message").getString("points"))
                            }
                            println(response)
                        },
                        Response.ErrorListener { response ->
                            println("error")
                            println(response)
                        }
                )

                requestQueue.add(objectRequest)
                startActivity(userPageIntent)
            }
            catch(e: Exception){
                Toast.makeText(this@FacebookLoginActivity, "Error here! " +e.toString(), Toast.LENGTH_LONG).show()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,gender,birthday,picture.type(large)")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
