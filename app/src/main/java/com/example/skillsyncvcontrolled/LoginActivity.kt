package com.example.skillsyncvcontrolled

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    private lateinit var txt:TextView
    private lateinit var name: TextInputLayout
    private lateinit var pwd: TextInputLayout
    private lateinit var login:Button
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var jsonObject: JSONCommunication
    private lateinit var homeActivityIntent: Intent
    private lateinit var collaborateActivityIntent: Intent
    private lateinit var progressBar: ProgressBar
    private lateinit var overlay: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Declaration..
        name=findViewById(R.id.textInputLayout)
        pwd=findViewById(R.id.passwordTextInputLayout)
        login=findViewById(R.id.button)
        dbHelper = DatabaseHelper(this)
        txt=findViewById(R.id.textView5)


        jsonObject = JSONCommunication()


        txt.setOnClickListener{
            val intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        // When Login button is clicked..
        login.setOnClickListener {
            setContentView(R.layout.progressbar_layout)
            progressBar = findViewById(R.id.progressBar)
            overlay = findViewById(R.id.overlay)
            val n= name.editText?.text?.toString()
            val p=pwd.editText?.text?.toString()
            // validating name
            if(n.isNullOrEmpty()){
                name.error="* Required"
                return@setOnClickListener
            }
            else{
                name.error=null
            }
            // validating password
            if(p.isNullOrEmpty()){
                pwd.error="* Required"
                return@setOnClickListener
            }
            else{
                pwd.error=null
            }

            if(name.error==null && pwd.error==null){
                val up=UserProfile(n,p)
                if(dbHelper.findUser(up)){
                    // Home activity intent
                    collaborateActivityIntent = Intent(this,CollaborateActivity::class.java)
                    homeActivityIntent= Intent(this,HomeActivity::class.java)
                    // Sending name to home activity
                    homeActivityIntent.putExtra("name",n)
                    Toast.makeText(this, "User Found", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.VISIBLE
                    overlay.visibility = View.VISIBLE
                    lifecycleScope.launch {
                        val response = serverResponse(dbHelper.getSkills(UserProfile(n,p)),jsonObject)
                        // filtering only user names from the json
                        val user_names = filterSkill(response)
                        println("User names: $user_names")
                        homeActivityIntent.putStringArrayListExtra("skillmap",user_names)

                        startActivity(homeActivityIntent)
                        setContentView(R.layout.activity_login)
                    }

                }
                else{
                    Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private suspend fun serverResponse(skills: List<String>, jsonObject: JSONCommunication): Map<String,List<String>>{
        return jsonObject.sendData(skills)
    }
    private fun filterSkill(map: Map<String,List<String>>): ArrayList<String>{
        val user_names = ArrayList<String>()
        for (key in map.keys){
            user_names.add(key)
        }
        return user_names
    }
}
