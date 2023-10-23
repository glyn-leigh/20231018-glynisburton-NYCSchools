package com.example.nysapplication.main.view

import SchoolDetailsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.NYCSchoolsTheme
import com.example.nysapplication.main.constants.AssetParamType
import com.example.nysapplication.main.view.screens.SchoolsHomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NYCSchoolsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )  {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.SchoolsHomeScreen.route
                    ) {
                        composable(
                            route = Screens.SchoolsHomeScreen.route
                        ) {
                            SchoolsHomeScreen(navController = navController)
                        }
                        composable(
                            route = Screens.SchoolDetailsScreen.route + "/{dbn}" + "/{name}" + "/{desc}" + "/{website}"

                        ) {
                            SchoolDetailsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }

    private fun navigateWithArguments(
        argument: String? = null,
        screen: Screens,
        navController: NavHostController
    ) {
        var route = screen.route
        // If argument is supplied, navigate using that argument
        argument?.let {
            route = screen.route.plus(it)
        }
        navController.navigate(route)
    }
}