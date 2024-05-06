package com.example.skillsyncvcontrolled

import android.widget.TextView
import okhttp3.Call
import okhttp3.FormBody
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject


class JSONCommunication {
    val url = "http://10.0.2.2:8000/recommendationapi/post/"



    fun sendData(skills: List<String>){
        val postBody = FormBody.Builder()
            .add("skills", skills.toString())
            .build()

        val request = Request.Builder()
            .url(url)
            .post(postBody)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()


            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val responseData = response.body?.string() ?: ""
                    println(responseData)
                    globalMessage = responseData
                }
            }

        })
    }

}