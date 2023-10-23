package com.example.nysapplication.main.viewmodel.schoolDetails

import com.example.nysapplication.main.model.SchoolsModel
import com.example.nysapplication.main.model.SchoolsSATModel

data class SchoolSATState (
    val isLoading: Boolean = false,
    val scores: SchoolsSATModel = SchoolsSATModel(),
    var currentSchool: SchoolsModel = SchoolsModel(),
    val error: String = ""


)