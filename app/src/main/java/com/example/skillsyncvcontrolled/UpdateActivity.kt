package com.example.skillsyncvcontrolled

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class UpdateActivity : AppCompatActivity() {
    private lateinit var home:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        home=findViewById(R.id.button3)
        home.setOnClickListener {
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}