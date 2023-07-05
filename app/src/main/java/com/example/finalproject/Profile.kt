package com.example.finalproject

class Profile {
    var firstName:String = ""
    var lastName:String = ""
    var userEmail:String = ""
    var profileImage:String = ""
    var userId:String = ""
    var imageId:String = ""
    var houseNo:String = ""

    constructor(
        firstName: String,
        lastName: String,
        userEmail: String,
        profileImage: String,
        userId: String,
        imageId:String,
        houseNo:String
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.userEmail = userEmail
        this.profileImage = profileImage
        this.userId = userId
        this.imageId = imageId
        this.houseNo = houseNo
    }
    constructor()
}

class Nav_Profile{}