package com.example.skillsyncvcontrolled

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        var pb=findViewById<ProgressBar>(R.id.progressBar4)
        pb.visibility= View.VISIBLE
        val handler= Handler(Looper.getMainLooper())
        val max=100
        var cur=0
        val runnable=object:Runnable{
            override fun run(){
                if(cur<max) {
                    cur++
                    pb.progress = cur
                    handler.postDelayed(this, 50)
                }
                else{
                    val intent=Intent(this@LoadingActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        handler.post(runnable)
    }
}