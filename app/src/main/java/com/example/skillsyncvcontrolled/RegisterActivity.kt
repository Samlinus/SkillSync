package com.example.skillsyncvcontrolled

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private lateinit var name: TextInputLayout
    private lateinit var pwd: TextInputLayout
    private lateinit var skills: TextInputLayout
    private lateinit var submit: Button
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        name = findViewById(R.id.nameTextInputLayout)
        pwd = findViewById(R.id.passwordTextInputLayout)
        skills = findViewById(R.id.textInputLayout)
        submit = findViewById(R.id.button2)
        dbHelper = DatabaseHelper(this)
        submit.setOnClickListener {
            val n = name.editText?.text?.toString()
            val p = pwd.editText?.text?.toString()
            val s = skills.editText?.text?.toString()
            if (n.isNullOrEmpty()) {
                name.error = "* Required"
                Toast.makeText(applicationContext, "Name Field is Empty!!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                name.error = null
            }
            if (p.isNullOrEmpty()) {
                pwd.error = "* Required"
                Toast.makeText(applicationContext, "Password Field is Empty!!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                pwd.error = null
            }
            if (s.isNullOrEmpty()) {
                skills.error = "* Required"
                Toast.makeText(applicationContext, "Skills Field is Empty!!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                skills.error = null
            }
            val userProfile=UserProfile(n,p,s)
            dbHelper.insertData(userProfile)
        }
    }
}