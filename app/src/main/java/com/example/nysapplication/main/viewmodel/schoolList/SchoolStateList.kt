package com.example.nysapplication.main.viewmodel.schoolList

import com.example.nysapplication.main.model.SchoolsModel

data class SchoolStateList(
    val isLoading: Boolean = false,
    val schools: List<SchoolsModel> = emptyList(),
    val error: String = ""
)

