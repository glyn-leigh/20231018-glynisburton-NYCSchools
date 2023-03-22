package com.example.nysapplication.main.model

data class SchoolsModel(
    val dbn: String,
    val school_name: String,
    val zip:String,
    val borough:String,
    val location:String,
    val overview_paragraph:String,
    val website:String,
    val total_students:Int,
    val graduation_rate:Float,
    val college_career_rate:Float,
    val academicopportunities1:String,
    val phone_number:String
)
