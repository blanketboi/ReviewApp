package com.example.reviewapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessControlContext
import java.util.ArrayList

//UserModel
val USER_DATABASE = "USER_TABLE"
val COLUMN_USER_ID = "ID"
val COLUMN_USERNAME = "USERNAME"
val COLUMN_PASSWORD = "PASSWORD"
val COLUMN_XP = "XP"

//RatingModel
val RATING_DATABASE = "RATING_TABLE"
val COLUMN_RATING_ID = "ID"
//val COLUMN_USER_ID = "USER_ID"
//val COLUMN_RESTAURANT_ID = "RESTAURANT_ID"
val COLUMN_SCORE = "SCORE"
val COLUMN_TITLE = "TITLE"
val COLUMN_DESCRIPTION = "DESCRIPTION"

//restaurantModel
val RESTAURANT_DATABASE = "RESTAURANT_TABLE"
val COLUMN_RESTAURANT_ID = "ID"
val COLUMN_RESTAURANT_NAME = "NAME"
val COLUMN_RESTAURANT_DESCRIPTION = "DESCRIPTION"
val COLUMN_EATIN = "EATIN"
val COLUMN_DELIVERY = "DELIVERY"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, USER_DATABASE, null, 5) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTable =
            "CREATE TABLE " + USER_DATABASE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_XP + " INT)"

        val createRatingTable =
            "CREATE TABLE " + RATING_DATABASE + " (" + COLUMN_RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_ID + " INTEGER FOREIGN KEY, " + COLUMN_RESTAURANT_ID + " INTEGER FOREIGN KEY, " + COLUMN_SCORE + " INT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT)"

        val createRestaurantTable =
            "CREATE TABLE " + RESTAURANT_DATABASE + " (" + COLUMN_RESTAURANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RESTAURANT_NAME + " TEXT, " + COLUMN_RESTAURANT_DESCRIPTION + " TEXT, " + COLUMN_EATIN + " INT, " + COLUMN_DELIVERY + " INT)"

        db?.execSQL(createUserTable)
        db?.execSQL(createRatingTable)
        db?.execSQL(createRestaurantTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USER_DATABASE")
        db?.execSQL("DROP TABLE IF EXISTS $RATING_DATABASE")
        db?.execSQL("DROP TABLE IF EXISTS $RESTAURANT_DATABASE")
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

    @SuppressLint("Range")
    fun userList(): ArrayList<UserModel> {
        val userList: ArrayList<UserModel> = ArrayList()
        val sql = "select * from $USER_DATABASE"
        val db = this.readableDatabase
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID))
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

    @SuppressLint("Range")
    fun restaurantList(): ArrayList<RestaurantModel> {
        val restaurantList: ArrayList<RestaurantModel> = ArrayList()
        val sql = "select * from $RESTAURANT_DATABASE"
        val db = this.readableDatabase
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_RESTAURANT_ID))
                val title = cursor.getString(cursor.getColumnIndex(COLUMN_RESTAURANT_NAME))
                val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                val eatIn = cursor.getInt(cursor.getColumnIndex(COLUMN_EATIN))
                val delivery = cursor.getInt(cursor.getColumnIndex(COLUMN_DELIVERY))

                val restaurant = RestaurantModel(
                    id = id,
                    title = title,
                    description = description,
                    eatIn = eatIn,
                    delivery = delivery
                )
                restaurantList.add(restaurant)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return restaurantList
    }

    //TODO: take username to delete row
    //TODO: update reviews with "Deleted User"
//    fun deleteUser() {
//        val db = this.writableDatabase
//        db.delete(USER_DATABASE, "$COLUMN_ID =?", arrayOf(id.toString()))
//        db.close()
//    }
}