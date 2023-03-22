package com.example.nysapplication.main.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val url = "https://data.cityofnewyork.us/resource/"

fun getBaseList(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

interface SchoolsApiInterfaceBase {
    // Request method and URL specified in the annotation
    @Headers(
        "Accept: application/json",
        "Content-type:application/json",
        "X-App-Token:tig5EG8qXyNaJCcSpceZO1rzT"
    )
    @GET("s3k6-pzi2.json")
    fun getSchools() : Call<List<SchoolsModel>>
    @GET("s3k6-pzi2.json")
    fun getSchoolByZip(@Query("zip") zip:String): Call<List<SchoolsModel>>
    @GET("s3k6-pzi2.json")
    //Taken out for time
    fun getSchoolByBorough(@Query("borough") borough:String): Call<List<SchoolsModel>>
    @GET("s3k6-pzi2.json")
    fun getSchoolByDBN(@Query("dbn") dbn:String): Call<List<SchoolsModel>>


}

interface SchoolsSATInterface {

    @Headers(
        "Accept: application/json",
        "Content-type:application/json",
        "X-App-Token:tig5EG8qXyNaJCcSpceZO1rzT"
    )
    @GET("f9bf-2cp4.json")

    fun getSAT(@Query("dbn") dbn:String): Call<List<SchoolsSATModel>>
}