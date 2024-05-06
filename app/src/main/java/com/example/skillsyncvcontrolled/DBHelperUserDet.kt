package com.example.skillsyncvcontrolled

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import android.database.sqlite.SQLiteDatabase as SQLiteDatabase1




class DatabaseHelper(private val context: Context): SQLiteOpenHelper(context,DBNAME,null,2){

    companion object{
        const val DBNAME = "skillSyncDB"
        const val TBNAME1 = "userDetails"
        const val TBNAME2 = "userskills"
        const val COLID  = "id"
        const val COLNAME = "name"
        const val COLPASS = "password"
        const val COLSKILLS = "skills"
    }
    override fun onCreate(db: SQLiteDatabase1?) {
        // Implemented when device doesn't contain database
        var createTableQuery = "create table " + TBNAME1 + " (" +
                COLID + " integer primary key autoincrement,"+
                COLNAME+ " varchar(30),"+
                COLPASS+ " varchar(30))"
        db?.execSQL(createTableQuery)
        println("Created user table")


        createTableQuery = "create table " + TBNAME2 + " (" +
                COLNAME + " varchar(30),"+
                COLSKILLS + " varchar(30))"

        db?.execSQL(createTableQuery)
        println("Created skills table")


    }

    override fun onUpgrade(db: SQLiteDatabase1?, oldVersion: Int, newVersion: Int) {
//        db?.execSQL("DROP TABLE IF EXISTS $TBNAME1")
//        db?.execSQL("DROP TABLE IF EXISTS $TBNAME2")
        onCreate(db)
    }

    fun insertData(user: UserProfile){
        val db = this.writableDatabase
        val cv = ContentValues()
        // Storing name and password...
        cv.put(COLNAME,user.name)
        cv.put(COLPASS,user.password)
        val result = db.insert(TBNAME1,null,cv)
        if (result == (-1).toLong()){
            Toast.makeText(context,"Insertion failed",Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(context,"Insertion succeeded",Toast.LENGTH_LONG).show()

    }

    fun insertSkill(userSkill: UserSkill) {
        val db = this.writableDatabase
        // Inserting each skill
        for (ele in userSkill.skills) {
            val cv = ContentValues()
            cv.put(COLNAME, userSkill.name)
            cv.put(COLSKILLS, ele)
            val result = db.insert(TBNAME2, null, cv)
            if (result == (-1).toLong())
                Toast.makeText(context, "Insertion failed", Toast.LENGTH_LONG).show()
        }
    }



        fun findUser(user: UserProfile): Boolean {
            val db = this.readableDatabase
            val selectQuery =
                "select * from $TBNAME1 where $COLNAME = '${user.name}' AND $COLPASS = '${user.password}'"
            val cursor = db.rawQuery(selectQuery, null)
            val status = cursor.moveToFirst()
            cursor.close()
            return status

    }

}

class UserProfile(var name: String,var password: String)
class UserSkill(var name: String,var skills: List<String>)
