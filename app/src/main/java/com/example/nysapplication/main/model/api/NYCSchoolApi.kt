package com.example.nysapplication.main.model.api

import com.example.nysapplication.main.constants.Constants
import com.example.nysapplication.main.model.SchoolsModel
import com.example.nysapplication.main.model.SchoolsSATModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolsApi {

    @GET(Constants.NYC_SCHOOLS_ENDPOINT)
    suspend fun getSchools(): List<SchoolsModel>

    @GET(Constants.NYC_SCHOOLS_ENDPOINT)
    suspend fun getSchoolsByZip(
        @Query("zip") zip:String
    ): List<SchoolsModel>

    @GET(Constants.NYC_SCORES_ENDPOINT)
    suspend fun getScores(
        @Query("dbn") dbn: String
    ): List<SchoolsSATModel>

}