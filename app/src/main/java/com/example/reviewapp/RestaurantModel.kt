package com.example.reviewapp

import android.location.Location

class RestaurantModel {
    var id : Int = 0
    var name : String = ""
    var description : String = ""
    var eatIn : Boolean = false
    var delivery : Boolean = false

    constructor(id: Int, name: String, description: String, eatIn: Boolean, delivery: Boolean) {
        this.id = id
        this.name = name
        this.description = description
        this.eatIn = eatIn
        this.delivery = delivery
    }

    constructor() {

    }

}