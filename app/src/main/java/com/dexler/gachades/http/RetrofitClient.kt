package com.dexler.gachades.http

import android.content.Context
import com.dexler.gachades.BuildConfig
import com.dexler.gachades.utils.Constants
import okhttp3.Cache
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.xml.datatype.DatatypeConstants.SECONDS


class RetrofitClient(ctx: Context) {
    var apiService: Api
    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(Api::class.java)
    }


    fun apiService():Api{
        return apiService
    }
}
