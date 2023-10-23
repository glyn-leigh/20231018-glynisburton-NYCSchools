package com.example.nysapplication.main.view.screens

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.nysapplication.main.model.SchoolsModel
import com.example.nysapplication.main.view.Screens
import com.example.nysapplication.main.view.items.SchoolsListItem
import com.example.nysapplication.main.viewmodel.schoolList.SchoolListViewModel
import com.google.gson.Gson
import java.util.Base64


@Composable
fun SchoolsHomeScreen (
        viewModel: SchoolListViewModel = hiltViewModel(),
        navController: NavController

    ){
        val state = viewModel.state.value
        val schoolList = state.schools

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(schoolList) {s ->
                    SchoolsListItem(school = s, onItemClick = {
                        val name = "/"+s.school_name
                        val desc = "/"+s.overview_paragraph
                        val encoder = Uri.encode(s.website)
                        val web = "/$encoder"

                        val identifier = "/"+s.dbn
                        navController.navigate((Screens.SchoolDetailsScreen.route + identifier + name + desc + web))
                    })
                }
            }

            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if(state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }