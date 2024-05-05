package com.example.skillsyncvcontrolled

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity() {
    private lateinit var txt:TextView
    private lateinit var name: TextInputLayout
    private lateinit var pwd: TextInputLayout
    private lateinit var login:Button
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        name=findViewById(R.id.textInputLayout)
        pwd=findViewById(R.id.passwordTextInputLayout)
        login=findViewById(R.id.button)
        dbHelper = DatabaseHelper(this)
        txt=findViewById(R.id.textView5)
        txt.setOnClickListener{
            val intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            val n= name.editText?.text?.toString()
            val p=pwd.editText?.text?.toString()
            if(n.isNullOrEmpty()){
                name.error="* Required"
                return@setOnClickListener
            }
            else{
                name.error=null
            }
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
                    val intent= Intent(this,HomeActivity::class.java)
                    intent.putExtra("name",n)
                    Toast.makeText(this, "User Found", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}