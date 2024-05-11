package com.example.skillsyncvcontrolled

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import org.json.JSONObject


class JSONCommunication {
    private val url = "http://10.0.2.2:8000/skillsync/post/"

    suspend fun sendData(skills: List<String>): Map<String, List<String>> {

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
            val skillsArray = jsonObject.getJSONArray(key)
            val skillList = ArrayList<String>()
            for (i in 0 until skillsArray.length()) {
                skillList.add(skillsArray.getString(i))
            }
            userSkillsMap[key] = skillList
        }
        return userSkillsMap
    }
}