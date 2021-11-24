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
        cv.put(COLUMN_XP, user.XP)
        var result = db.insert(USER_DATABASE, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Error creating database", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun userList(): MutableList<UserModel> {
        val sql = "select * from $USER_DATABASE"
        val db = this.readableDatabase
        val storeUserModel = arrayListOf<UserModel>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = Integer.parseInt(cursor.getString(0))
                val username = cursor.getString(1)
                val password = cursor.getString(2)
                val xp = Integer.parseInt(cursor.getString(3))
                storeUserModel.add(UserModel(username, password, xp))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return storeUserModel
    }

    fun findUser(username: String) : UserModel? {
        val query = "SELECT * FROM $USER_DATABASE WHERE $COLUMN_USERNAME = $username"
        val db = this.writableDatabase
        var mUser = UserModel? = null
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            val username = cursor.getString(1)
            mUser = UserModel()
        }
        cursor.close()
        return mUser
    }

    fun deleteUser() {
        val db = this.writableDatabase
        db.delete(USER_DATABASE, "$COLUMN_ID =?", arrayOf(id.toString()))
        db.close()
    }
}