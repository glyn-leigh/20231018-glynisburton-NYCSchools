package com.example.nysapplication.main.respository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nysapplication.main.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface NYCRepository {

        suspend fun getSchools(): List<SchoolsModel>

        suspend fun getScores(dbn: String): List<SchoolsSATModel>
    }


