package com.example.skillsyncvcontrolled

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import org.json.JSONObject



class JSONCommunication {
    private val url = "http://10.0.2.2:8000/skillsync/post/"

    suspend fun sendData(skills: List<String>): Map<String, List<String>> {
        lateinit var responseMap: Map<String, List<String>>

        return withContext(Dispatchers.IO) {
            val postBody = FormBody.Builder()
                .add("skills", skills.toString())
                .build()

            val request = Request.Builder()
                .url(url)
                .post(postBody)
                .build()

            val client = OkHttpClient()

            val response = client.newCall(request).execute()

            if (!response.isSuccessful) {
                throw IOException("Unexpected code ${response.code}")
            }

            val responseData = response.body?.string() ?: ""
            processResponse(responseData)
        }
    }

    private fun processResponse(responseData: String): Map<String, List<String>> {
        println(responseData)
        val jsonObject = JSONObject(responseData)
        val userSkillsMap = HashMap<String, List<String>>()
        for (key in jsonObject.keys()) {
            val user = key
            val skillsArray = jsonObject.getJSONArray(key)
            val skillList = ArrayList<String>()
            for (i in 0 until skillsArray.length()) {
                skillList.add(skillsArray.getString(i))
            }
            userSkillsMap[user] = skillList
        }
        return userSkillsMap
    }
}


//class JSONCommunication {
//    private val url = "http://10.0.2.2:8000/skillsync/post/"
//
//    fun sendData(skills: List<String>): Map<String, List<String>> {
//        var responseMap: Map<String, List<String>> = HashMap<String,List<String>>()
//        val postBody = FormBody.Builder()
//            .add("skills", skills.toString())
//            .build()
//
//        val request = Request.Builder()
//            .url(url)
//            .post(postBody)
//            .build()
//
//        val client = OkHttpClient()
//        client.newCall(request).enqueue(object : okhttp3.Callback {
//            override fun onFailure(call: okhttp3.Call, e: IOException) {
//                e.printStackTrace()
//            }
//
//            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response){
//                response.use {
//                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
//                    val responseData = response.body?.string() ?: ""
//                    responseMap = processResponse(responseData)
//                }
//            }
//
//        })
//
//        return responseMap
//
//    }
//
//    fun processResponse(responseData: String): HashMap<String, List<String>> {
//        val jsonObject = JSONObject(responseData)
//        val userSkillsMap = HashMap<String,List<String>>()
//        for( key in jsonObject.keys()){
//            val user = key
//            val skillsArray = jsonObject.getJSONArray(key)
//            var skillList = ArrayList<String>()
//            for (i in 0 until skillsArray.length()){
//                skillList.add(skillsArray.getString(i))
//            }
//            userSkillsMap[user] = skillList
//        }
//
//        return userSkillsMap
//    }
//
//}