package life.league.challenge.kotlin.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * [LoaderScreen]
 *
 * A composable function that displays a loading screen with a rotating loader icon and a "Loading" text.
 * This can be used to inform the user that a process is ongoing.
 */
@Composable
fun LoaderScreen() {
    // Creates a rotating animation using a state that updates continuously.
    val rotate = produceState(initialValue = 0) {
        while (true) {
            delay(14) // Delay to control the rotation speed.
            value = (value + 5) % 360 // Rotates the icon incrementally.
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Loading",
                modifier = Modifier
                    .size(60.dp)
                    .rotate(rotate.value.toFloat())
            )
            Text(
                text = "Loading",
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
    }
}