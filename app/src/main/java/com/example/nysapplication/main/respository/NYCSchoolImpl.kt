package com.example.nysapplication.main.respository

import com.example.nysapplication.main.model.SchoolsModel
import com.example.nysapplication.main.model.SchoolsSATModel
import com.example.nysapplication.main.model.api.SchoolsApi

class NYCSchoolImpl(
    private val api: SchoolsApi
): NYCRepository {

    //extra abstraction layer to further separate concerns
    override suspend fun getSchools(): List<SchoolsModel> {
        return api.getSchools()
    }

    override suspend fun getScores(dbn: String): List<SchoolsSATModel> {
        return api.getScores(dbn = dbn)
    }
}