package com.example.skillsyncvcontrolled

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {
    private lateinit var edit:ImageView
    private lateinit var txt:TextView
    private var appendText=" "
    private lateinit var bn:BottomNavigationView
    private lateinit var jsonObject: JSONCommunication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        edit = findViewById(R.id.imageView2)
        txt = findViewById(R.id.textView13)
        jsonObject = JSONCommunication()

        val name = intent.getStringExtra("name")
        println("Home page")
        val user_names = intent.getStringArrayListExtra("skillmap")
        val currentText = txt.text.toString()
        appendText = "$currentText $name"
        txt.text = appendText

        edit.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }


        bn=findViewById(R.id.bottom_navigation)

        //makeCurrentFragment(homeFragment)

        bn.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.ic_home-> {
                    //makeCurrentFragment(HomeFragment())
                    true
                }
                R.id.ic_profile-> {
                    //makeCurrentFragment(ProfileFragment())
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.ic_feed-> {
                    //makeCurrentFragment(FeedFragment())
                    val intent = Intent(this, CollaborateActivity::class.java)
                    intent.putStringArrayListExtra("skillmap",user_names)
                    startActivity(intent)
                    true
                }
                else-> false
            }
        }

    }


    override fun onResume() {
        super.onResume()
        txt.text = appendText
    }

}
