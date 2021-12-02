package com.example.reviewapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessControlContext
import java.util.ArrayList

val USER_DATABASE = "USER_TABLE"
val COLUMN_ID = "ID"
val COLUMN_USERNAME = "USERNAME"
val COLUMN_PASSWORD = "PASSWORD"
val COLUMN_XP = "XP"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, USER_DATABASE, null, 5) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + USER_DATABASE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_XP + " INT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USER_DATABASE")
        onCreate(db)
    }

    fun insertData(user: UserModel) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COLUMN_USERNAME, user.username)
        cv.put(COLUMN_PASSWORD, user.password)
        cv.put(COLUMN_XP, user.xp)
        var result = db.insert(USER_DATABASE, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Error creating database", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun userList(): ArrayList<UserModel> {
        val userList: ArrayList<UserModel> = ArrayList()
        val sql = "select * from $USER_DATABASE"
        val db = this.readableDatabase
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME))
                val password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
                val xp = cursor.getInt(cursor.getColumnIndex(COLUMN_XP))

                val user = UserModel(
                    id = id,
                    username = username,
                    password = password,
                    xp = xp
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return userList
    }

    //TODO: take username to delete row
    //TODO: update reviews with "Deleted User"
//    fun deleteUser() {
//        val db = this.writableDatabase
//        db.delete(USER_DATABASE, "$COLUMN_ID =?", arrayOf(id.toString()))
//        db.close()
//    }
}