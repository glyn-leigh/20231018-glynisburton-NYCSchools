package com.example.nysapplication.main.constants

object Constants {
    const val BASEURL = "https://data.cityofnewyork.us/resource/"
    //HS Endpoint
    const val NYC_SCHOOLS_ENDPOINT = "s3k6-pzi2.json"
    //SAT Endpoint
    const val NYC_SCORES_ENDPOINT = "f9bf-2cp4.json"
    //Used to grab from SAT Endpoint, dbn is provided by schools response
    const val PARAM_SCHOOL_DBN = "dbn"
    //
    const val PARAM_SCHOOL_ZIP="zip"
    const val PARAM_SCHOOL_BORO="borough"
    const val SAT_VALUE_EMPTY="no_sat"

}