package com.example.skillsyncvcontrolled

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CollaborateActivity : AppCompatActivity(){

    private var nameList= mutableListOf<String>()
    private var skillsList= mutableListOf<String>()
    private var imagesList= mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collaborate)

        postToList()

        val rv=findViewById<RecyclerView>(R.id.rv_recyclerView)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = RecyclerAdapter(nameList,skillsList,imagesList)
    }
    private fun addToList(name:String,skills:String,images:Int) {
        nameList.add(name)
        skillsList.add(skills)
        imagesList.add(images)
    }
    private fun postToList(){
        for(i in 1..25){
            addToList("Name $i", "Skills $i", R.mipmap.ic_launcher_round)
        }
    }
}