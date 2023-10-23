package com.example.nysapplication.main.view.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.nysapplication.main.model.SchoolsModel

@Composable
fun SchoolsListItem (
    school: SchoolsModel,
    onItemClick: (SchoolsModel) -> Unit,
) {

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally


                ) {

                Text(text = school.school_name, style = MaterialTheme.typography.headlineSmall)
                Text (text =school.academicopportunities1, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onItemClick(school) },
                modifier = Modifier
                    .width(200.dp)
                    .height(40.dp)

            ) {
                Text(text = "See More")
            }
                Spacer(modifier = Modifier.height(16.dp))
                Divider()


        }


}