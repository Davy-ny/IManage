package com.example.finalproject

class Profile {
    var firstName:String = ""
    var lastName:String = ""
    var userEmail:String = ""
    var profileImage:String = ""
    var userId:String = ""
    var imageId:String = ""

    constructor(
        firstName: String,
        lastName: String,
        userEmail: String,
        profileImage: String,
        userId: String,
        imageId:String
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.userEmail = userEmail
        this.profileImage = profileImage
        this.userId = userId
        this.imageId = imageId
    }
    constructor()
}