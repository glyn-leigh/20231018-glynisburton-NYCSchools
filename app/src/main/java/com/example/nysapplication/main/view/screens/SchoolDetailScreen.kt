import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nysapplication.main.constants.Constants
import com.example.nysapplication.main.view.Screens
import com.example.nysapplication.main.viewmodel.schoolDetails.SchoolSATListViewModel
import com.example.nysapplication.main.viewmodel.schoolList.SchoolListViewModel

@Composable
fun SchoolDetailsScreen(
    viewModel: SchoolSATListViewModel = hiltViewModel(),
    navController: NavController


    ) {
    val satState = viewModel.state.value
    val score = satState.scores
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column (
            Modifier.padding(
                30.dp, 20.dp
            )
        ){
            Text(
                text = satState.currentSchool.school_name,
                style= MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "SAT Scores", style= MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(20.dp))

            if (satState.error.isNotBlank()) {
                when (satState.error) {
                    Constants.SAT_VALUE_EMPTY -> {
                        Text(
                            text = "This school hasn't updated their SAT Score information.",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)


                        )

                    }
                }
            } else {


                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "Math: " + score.sat_math_avg_score)
                    Text(text = "Reading: " + score.sat_critical_reading_avg_score)
                    Text(text = "Writing: " + score.sat_writing_avg_score)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = satState.currentSchool.overview_paragraph)

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "School Contact Info", style = MaterialTheme.typography.headlineSmall)

            //Here we can handle navigating to the website, what to do if the user have no browser apps, ect.
            TextButton(onClick = {}) {
                Text (text = satState.currentSchool.website, style = MaterialTheme.typography.titleLarge)
            }

            Spacer(modifier = Modifier.height(10.dp))



        }


        if (satState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }



}

