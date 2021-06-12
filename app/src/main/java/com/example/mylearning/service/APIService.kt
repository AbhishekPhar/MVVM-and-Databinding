package com.melimu.exam.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @GET("/talzo/dummy/test/testing_data")
    fun getExample(): Call<ResponseBody?>?
}