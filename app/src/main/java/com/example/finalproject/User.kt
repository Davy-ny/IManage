package com.example.finalproject

class User{
    var firstName : String = ""
    var lastName : String = ""
    var userEmail : String = ""
    var profileImage : String = ""
    var houseNo : String = ""
    var isChecked : Boolean = false

    constructor(firstName: String, lastName: String, userEmail: String, profileImage: String, houseNo: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.userEmail = userEmail
        this.profileImage = profileImage
        this.houseNo = houseNo
    }
    constructor()
    fun getIsChecked():Boolean = this.isChecked
    fun setIsChecked(isChecked:Boolean){
        this.isChecked = isChecked
    }
}
