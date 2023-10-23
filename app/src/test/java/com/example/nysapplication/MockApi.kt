package com.example.nysapplication

import com.example.nysapplication.main.model.SchoolsModel
import com.example.nysapplication.main.model.SchoolsSATModel

class MockApi {

        private val schools = listOf<SchoolsModel>()
        private val scores = listOf<SchoolsSATModel>()

        suspend fun getSchools(): List<SchoolsModel> {

            return schools
        }

        suspend fun getScores(dbn: String): List<SchoolsSATModel> {
            return scores
        }


}