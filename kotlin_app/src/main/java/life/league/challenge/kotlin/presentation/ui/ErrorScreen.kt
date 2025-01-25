package life.league.challenge.kotlin.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import life.league.challenge.kotlin.R

/**
 * [ErrorScreen]
 *
 * A composable function that displays a user-friendly error screen with an error image
 * and a descriptive error message. This can be used to inform users of an issue in the app.
 *
 * @param errorMessage The message to be displayed, describing the error.
 */
@Composable
fun ErrorScreen(errorMessage: String) {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Image(painter = painterResource(id = R.drawable.error),
                contentDescription = "Error Image",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(text = errorMessage,
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen("Couldn't reach server. Check your internet connection.")
}