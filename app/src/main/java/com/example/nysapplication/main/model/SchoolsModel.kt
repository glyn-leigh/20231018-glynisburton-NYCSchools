package com.example.nysapplication.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SchoolsModel(
    val dbn: String = "",
    var school_name: String = "",
    val zip:String = "",
    val borough:String = "",
    val location:String = "",
    var overview_paragraph:String = "",
    val website:String = "",
    val total_students:Int = 0,
    val graduation_rate:Float = 0f,
    val college_career_rate:Float = 0f,
    val academicopportunities1:String = "",
    val phoneNumber:String = ""
): Parcelable
