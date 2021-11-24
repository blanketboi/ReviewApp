package com.example.reviewapp

class UserModel {
    var id : Int = 0
    var username : String = ""
    var password : String = ""
    var XP : Int = 0

    constructor( username: String, password: String, XP: Int) {
        this.username = username
        this.password = password
        this.XP = XP
    }

    constructor() {

    }

}