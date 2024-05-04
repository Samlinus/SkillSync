package com.example.skillsyncvcontrolled

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var edit:ImageView
    private lateinit var txt:TextView
    private var appendText=" "
    private lateinit var bn:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        edit = findViewById(R.id.imageView2)
        txt = findViewById(R.id.textView13)


        val name = intent.getStringExtra("name")
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
                    val intent = Intent(this, com.example.skillsyncvcontrolled.CollaborateActivity::class.java)
                    startActivity(intent)
                    true
                }
                else-> false
            }
        }
    }

    private fun  makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_wrapper, fragment).commit()
    }

    override fun onResume() {
        super.onResume()
        txt.text = appendText
    }

}