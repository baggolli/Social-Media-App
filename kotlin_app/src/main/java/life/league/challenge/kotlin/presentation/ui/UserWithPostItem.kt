package life.league.challenge.kotlin.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.domain.models.UserWithPost

/**
 * [UserWithPostItem]
 *
 * A composable function to display a single user's post details, including the avatar, username, title, and description.
 * @param item The [UserWithPost] object containing details about the user and the post to be displayed.
 */
@Composable
fun UserWithPostItem(item: UserWithPost) {
    Column(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
            .fillMaxWidth()
    ) {
        // Row for user avatar and username
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(item.userAvatar),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(54.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape)
            )
            Text(
                text = item.username,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                fontSize = 18.5.sp,
                color = colorResource(id = R.color.black)
            )
        }
        // Displays the title of the post
        Text(
            text = item.title,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 8.dp, top = 6.dp, end = 8.dp, bottom = 6.dp),
            color = colorResource(id = R.color.black)
        )
        // Displays the description of the post
        Text(
            text = item.description,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
            color = colorResource(id = R.color.black)
        )
        // Adds a separator line between items
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.lineSeparator))
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}