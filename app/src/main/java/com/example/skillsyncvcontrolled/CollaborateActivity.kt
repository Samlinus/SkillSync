package com.example.skillsyncvcontrolled

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CollaborateActivity : AppCompatActivity(){

    private var nameList= mutableListOf<String>()
    private var skillsList= mutableListOf<String>()
    private var imagesList= mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        println("On create")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collaborate)
        // Generate recycler view..

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
        val user_names = intent.getStringArrayListExtra("skillmap")
        println("name: "+intent.getStringExtra("n"))
        println(user_names)
        if (user_names != null) {
            println("user names not null..")
            for(name in user_names){
                addToList(name, "Skills", R.mipmap.ic_launcher_round)
            }
        }
        else{
            println("View updated..")
        }
    }

    override fun onResume() {
        super.onResume()
        postToList()
    }
}