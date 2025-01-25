package life.league.challenge.kotlin.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.domain.models.UserWithPost

/**
 * [UserWithPosts]
 *
 * A composable function to display a list of user posts using a vertical scrolling list.
 * @param userWithPosts A list of [UserWithPost] objects to be displayed in the UI.
 */
@Composable
fun UserWithPosts(userWithPosts: List<UserWithPost>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Heading Text
        Text(
            text = "Posts",
            fontSize = 24.sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.SemiBold
        )
        // Separator line below the title
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.lineSeparator))
                .padding(1.dp)
                .fillMaxWidth()
                .height(1.dp)
        )
        // LazyColumn for displaying the list of posts
        LazyColumn {
            items(userWithPosts.size) {
                val item = userWithPosts[it]
                UserWithPostItem(item)
            }
        }
    }
}