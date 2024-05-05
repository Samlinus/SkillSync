package com.example.skillsyncvcontrolled

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import android.database.sqlite.SQLiteDatabase as SQLiteDatabase1

const val DBNAME = "skillSyncDB"
const val TBNAME = "userDetails"
const val COLID  = "id"
const val COLNAME = "name"
const val COLPASS = "password"
const val COLSKILLS = "skills"

class DatabaseHelper(private val context: Context): SQLiteOpenHelper(context,DBNAME,null,1){

    override fun onCreate(db: SQLiteDatabase1?) {
        // Implemented when device doesn't contain database
        val createTableQuery = "create table " + TBNAME + " (" +
                COLID + " integer primary key autoincrement,"+
                COLNAME+ " varchar(30),"+
                COLPASS+ " varchar(30),"+
                COLSKILLS+ " varchar(50))"

        db?.execSQL(createTableQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase1?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TBNAME")
        onCreate(db)
    }

    fun insertData(user: UserProfile){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLNAME,user.name)
        cv.put(COLPASS,user.password)
        cv.put(COLSKILLS,user.skills)
        val result = db.insert(TBNAME,null,cv)
        if (result == (-1).toLong()){
            Toast.makeText(context,"Insertion failed",Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(context,"Insertion succeeded",Toast.LENGTH_LONG).show()
    }

    fun findUser(user: UserProfile): Boolean {
        val db = this.readableDatabase
        val selectQuery = "select * from $TBNAME where $COLNAME = '${user.name}' AND $COLPASS = '${user.password}'"
        val cursor = db.rawQuery(selectQuery,null)
        val status = cursor.moveToFirst()
        cursor.close()
        return status

    }
}

class UserProfile(var name: String,var password: String,var skills: String ="")