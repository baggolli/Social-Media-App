package life.league.challenge.kotlin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import life.league.challenge.kotlin.common.Resource
import life.league.challenge.kotlin.presentation.ui.ErrorScreen
import life.league.challenge.kotlin.presentation.ui.LoaderScreen
import life.league.challenge.kotlin.presentation.ui.UserWithPosts
import life.league.challenge.kotlin.presentation.viewmodel.UserWithPostViewModel

/**
 * [MainActivity]
 *
 * The entry point for the Android application. This class is annotated with `@AndroidEntryPoint`
 * to enable dependency injection using Hilt. The main UI is composed using Jetpack Compose.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * The lifecycle callback triggered when the activity is created.
     * Sets the content of the activity using the `UserWithPostScreen` composable.
     *
     * @param savedInstanceState The saved state of the activity, if any.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserWithPostScreen()
        }
    }

    /**
     * [UserWithPostScreen]
     *
     * A composable function that renders the main screen of the application.
     * Displays a loading screen, user posts, or an error screen based on the UI state from the view model.
     */
    @Composable
    fun UserWithPostScreen() {
        val viewModel: UserWithPostViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is Resource.Loading -> {
                // Show a loading indicator while data is being fetched.
                LoaderScreen()
            }
            is Resource.Success -> {
                // Show the list of user posts when data is successfully fetched.
                val userWithPosts = (uiState as Resource.Success).data
                userWithPosts?.let { UserWithPosts(it) }
            }
            is Resource.Error -> {
                // Show an error screen with the error message if an error occurs.
                val errorMessage = (uiState as Resource.Error).message
                errorMessage?.let { ErrorScreen(it) }
            }
        }
    }
}
