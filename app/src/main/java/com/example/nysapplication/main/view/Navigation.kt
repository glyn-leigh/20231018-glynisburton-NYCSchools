package com.example.nysapplication.main.view

sealed class Screens(val route: String) {
    data object SchoolsHomeScreen: Screens("school_home_screen")
    data object SchoolDetailsScreen: Screens("school_details_screen")
}

