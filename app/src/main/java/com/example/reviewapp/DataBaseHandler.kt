package com.example.reviewapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessControlContext

val USER_DATABASE = "USER_TABLE"
val COLUMN_ID = "ID"
val COLUMN_USERNAME = "USERNAME"
val COLUMN_PASSWORD = "PASSWORD"
val COLUMN_XP = "XP"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, USER_DATABASE, null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + USER_DATABASE + " (" + COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_XP + " INT)"
        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(user : UserModel) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COLUMN_USERNAME, user.username)
        cv.put(COLUMN_PASSWORD, user.password)
        cv.put(COLUMN_XP, user.XP)
        var result = db.insert(USER_DATABASE, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Error creating database", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData() : MutableList<UserModel> {
        var list : MutableList<UserModel> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + USER_DATABASE
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()) {
            do {
                var user = UserModel()
                user.id = result.getString(result.getColumnIndex(COLUMN_ID)).toInt()
                user.username = result.getString(result.getColumnIndex(COLUMN_USERNAME))
                user.password = result.getString(result.getColumnIndex(COLUMN_PASSWORD))
                user.XP = result.getString(result.getColumnIndex(COLUMN_XP)).toInt()
                list.add(user)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun deleteData() {
        val db = this.writableDatabase
        db.delete(USER_DATABASE, COLUMN_ID + "=?", arrayOf(1.toString()))
        db.close()
    }
}