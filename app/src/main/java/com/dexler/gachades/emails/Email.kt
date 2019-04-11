package com.dexler.gachades.emails

class Email(var _id:String, var email: String, var createdAt:String){

    override fun toString(): String {
        return "Email(_id=$_id, email='$email', createdAt='$createdAt')"
    }
}