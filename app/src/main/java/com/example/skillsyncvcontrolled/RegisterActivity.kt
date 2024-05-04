package com.example.skillsyncvcontrolled

import android.content.ContentValues
//import android.content.Intent
//import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
//import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private lateinit var name:TextInputLayout
    private lateinit var pwd:TextInputLayout
    private lateinit var skills:TextInputLayout
    private lateinit var submit:Button
    private lateinit var dbHelper:DatabaseHelper
    private lateinit var database:SQLiteDatabase
    //private lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        name = findViewById(R.id.nameTextInputLayout)
        pwd = findViewById(R.id.passwordTextInputLayout)
        skills = findViewById(R.id.textInputLayout)
        submit = findViewById(R.id.button2)
        //tv=findViewById(R.id.textView6)
        dbHelper=DatabaseHelper(this)
        database=dbHelper.writableDatabase
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
            insertData(n,s)
            //val cursor=viewData()
            //showData(cursor)
        }

    }
    /*private fun showData(cursor: Cursor) {
        val stringBuilder=StringBuilder()
        while(cursor.moveToNext()){
            val id=cursor.getInt(0)
            val name=cursor.getString(1)
            val skills=cursor.getString(2)
            stringBuilder.append("ID : $id, Name : $name , skills : $skills\n")
        }
        cursor.close()
        tv.text=stringBuilder.toString()
    }

    private fun viewData(): Cursor {

        return database.query(
            "skill",
            arrayOf("id","name","skills"),
            null,
            null,
            null,
            null,
            null)
    }*/
    private fun insertData(name1: String, skills: String) {
        val values= ContentValues()
        values.put("name",name1)
        values.put("skills",skills)
        database.insert("skill",null,values)
        Toast.makeText(applicationContext,"Data is inserted",Toast.LENGTH_SHORT).show()
    }
}