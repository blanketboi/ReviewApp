package com.example.reviewapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import kotlin.collections.ArrayList

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
val COLUMN_CUISINE = "CUISINE"
val COLUMN_EATIN = "EATIN"
val COLUMN_DELIVERY = "DELIVERY"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, USER_DATABASE, null, 5) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTable =
            "CREATE TABLE " + USER_DATABASE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_XP + " INT)"

        val createRatingTable =
            "CREATE TABLE " + RATING_DATABASE + " (" + COLUMN_RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_ID + " INTEGER FOREIGN KEY, " + COLUMN_RESTAURANT_ID + " INTEGER FOREIGN KEY, " + COLUMN_SCORE + " INT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT)"

        val createRestaurantTable =
            "CREATE TABLE " + RESTAURANT_DATABASE + " (" + COLUMN_RESTAURANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RESTAURANT_NAME + " TEXT, " + COLUMN_RESTAURANT_DESCRIPTION + " TEXT, " + COLUMN_CUISINE + " TEXT, " + COLUMN_EATIN + " INT, " + COLUMN_DELIVERY + " INT)"

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

    fun insertUser(user: UserModel) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_USERNAME, user.username)
        cv.put(COLUMN_PASSWORD, user.password)
        cv.put(COLUMN_XP, user.xp)
        val result = db.insert(USER_DATABASE, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Error creating database", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "User Created", Toast.LENGTH_SHORT).show()
        }
    }

    fun getUserID(username: String, password: String): Int {
        val db = this.writableDatabase
        val sql =
            "SELECT $COLUMN_USER_ID from $USER_DATABASE where $COLUMN_USERNAME = " + username + " and $COLUMN_PASSWORD = " + password
        val cursor = db.rawQuery(sql, null)
        return cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID))
    }

    fun getUserXP(userID : Int) : Int {
        val db = this.writableDatabase
        val sql = "SELECT $COLUMN_XP from $USER_DATABASE where $COLUMN_USER_ID = " + userID
        val cursor = db.rawQuery(sql, null)
        return cursor.getInt(cursor.getColumnIndex(COLUMN_XP))
    }

    fun setUserXP(userID : Int, userXP : Int) {
        val db = this.writableDatabase
        val sql = "UPDATE $USER_DATABASE set $COLUMN_XP = " + userXP + " where $COLUMN_USER_ID = " + userID
        db.rawQuery(sql, null)
    }

    fun getRestaurant(restaurantID : Int) : RestaurantModel {
        val db = this.writableDatabase
        val sql = "SELECT * from $RESTAURANT_DATABASE where $COLUMN_RESTAURANT_ID = " + restaurantID
        val cursor = db.rawQuery(sql, null)

        val title = cursor.getString(cursor.getColumnIndex(COLUMN_RESTAURANT_NAME))
        val description = cursor.getString(cursor.getColumnIndex(COLUMN_RESTAURANT_DESCRIPTION))
        val cuisine = cursor.getString(cursor.getColumnIndex(COLUMN_CUISINE))
        val eatIn = cursor.getInt(cursor.getColumnIndex(COLUMN_EATIN))
        val delivery = cursor.getInt(cursor.getColumnIndex(COLUMN_DELIVERY))

        return RestaurantModel(
            id = restaurantID,
            title = title,
            description = description,
            cuisine = cuisine,
            eatIn = eatIn,
            delivery = delivery)
    }

    fun insertRating(rating: RatingModel) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_USER_ID, rating.userID)
        cv.put(COLUMN_RESTAURANT_ID, rating.restaurantID)
        cv.put(COLUMN_SCORE, rating.score)
        cv.put(COLUMN_TITLE, rating.title)
        cv.put(COLUMN_DESCRIPTION, rating.description)
        val result = db.insert(RATING_DATABASE, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Error creating database", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Review Posted", Toast.LENGTH_SHORT).show()
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

    //TODO: where restaurant
    //TODO: where user
    @SuppressLint("Range")
    fun ratingList(): ArrayList<RatingModel> {
        val ratingList: ArrayList<RatingModel> = ArrayList()
        val sql = "select * from $RATING_DATABASE"
        val db = this.readableDatabase
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_RATING_ID))
                val userID = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID))
                val restaurantID = cursor.getInt(cursor.getColumnIndex(COLUMN_RESTAURANT_ID))
                val score = cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE))
                val title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))

                val rating = RatingModel(
                    id = id,
                    userID = userID,
                    restaurantID = restaurantID,
                    score = score,
                    title = title,
                    description = description
                )
                ratingList.add(rating)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return ratingList
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
                val description = cursor.getString(cursor.getColumnIndex(COLUMN_RESTAURANT_DESCRIPTION))
                val cuisine = cursor.getString(cursor.getColumnIndex(COLUMN_CUISINE))
                val eatIn = cursor.getInt(cursor.getColumnIndex(COLUMN_EATIN))
                val delivery = cursor.getInt(cursor.getColumnIndex(COLUMN_DELIVERY))

                val restaurant = RestaurantModel(
                    id = id,
                    title = title,
                    description = description,
                    cuisine = cuisine,
                    eatIn = eatIn,
                    delivery = delivery
                )
                restaurantList.add(restaurant)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return restaurantList
    }

    fun restaurantSearch(textBar : String, eatIn : Int, delivery : Int) : ArrayList<RestaurantModel> {
        var textSQL : String = ""
        var eatInSQL : String = ""
        var deliverySQL : String = ""
        var sql : String = ""
        if (textBar.isNotEmpty()){
            textSQL = "$COLUMN_RESTAURANT_NAME LIKE " + textBar + "%"
            if (eatIn == 1) {
                eatInSQL = "$COLUMN_EATIN = " + 1
                if (delivery == 1) {
                    deliverySQL = "$COLUMN_DELIVERY = " + 1
                    sql = "SELECT * from $RESTAURANT_DATABASE where " + textSQL + " and " + eatInSQL + " and " + deliverySQL
                } else {
                    sql = "SELECT * from $RESTAURANT_DATABASE"
                }
            }
        }
        val restaurantList : ArrayList<RestaurantModel> = ArrayList()
        val db = this.readableDatabase
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_RESTAURANT_ID))
                val title = cursor.getString(cursor.getColumnIndex(COLUMN_RESTAURANT_NAME))
                val description = cursor.getString(cursor.getColumnIndex(COLUMN_RESTAURANT_DESCRIPTION))
                val cuisine = cursor.getString(cursor.getColumnIndex(COLUMN_CUISINE))
                val eatIn = cursor.getInt(cursor.getColumnIndex(COLUMN_EATIN))
                val delivery = cursor.getInt(cursor.getColumnIndex(COLUMN_DELIVERY))

                val restaurant = RestaurantModel(
                    id = id,
                    title = title,
                    description = description,
                    cuisine = cuisine,
                    eatIn = eatIn,
                    delivery = delivery
                )
                restaurantList.add(restaurant)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return restaurantList

    }

    fun deleteUser(id : Int) {
        val db = this.writableDatabase
        db.delete(USER_DATABASE, "$COLUMN_USER_ID =?", arrayOf(id.toString()))
        val sql = "UPDATE $RATING_DATABASE set $COLUMN_USER_ID = NULL where $COLUMN_USER_ID = " + id
        db.rawQuery(sql, null)
        db.close()
    }
}