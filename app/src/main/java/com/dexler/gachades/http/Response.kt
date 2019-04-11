package com.dexler.gachades.http

class Response<T:Any>(var status:Int, var message:String, var data:T){
    override fun toString(): String {
        return "Response(status=$status, message='$message', data=$data)"
    }
}