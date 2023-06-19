package com.example.finalproject

class User{
    var firstName : String = ""
    var lastName : String = ""
    var userEmail : String = ""
    var profileImage : String = ""

    constructor(firstName: String, lastName: String, userEmail: String, profileImage: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.userEmail = userEmail
        this.profileImage = profileImage
    }
    constructor()
}
