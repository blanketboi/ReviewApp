package com.example.reviewapp

class RatingModel {
    var id : Int = 0
    var userID : Int = 0
    var restaurantID : Int = 0
    var score : Int = 0
    var title : String = ""
    var description : String = ""

    constructor(id: Int, userID: Int, restaurantID : Int, score: Int, title: String, description: String) {
        this.id = id
        this.userID = userID
        this.restaurantID = restaurantID
        this.score = score
        this.title = title
        this.description = description
    }

    constructor() {
    }
}