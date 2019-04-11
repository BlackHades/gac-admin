package com.dexler.gachades.http

import com.dexler.gachades.emails.Email
import com.dexler.gachades.installations.Installation
import retrofit2.Call
import retrofit2.http.GET

interface  Api{
    @GET("installations/emails/fetch")
    fun getEmails() : Call<Response<ArrayList<Email>>>



    @GET("installations/fetch")
    fun getInstallations() : Call<Response<ArrayList<Installation>>>
}