package life.league.challenge.kotlin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.common.Resource
import life.league.challenge.kotlin.domain.models.UserWithPost
import life.league.challenge.kotlin.domain.usecase.UserWithPostUseCase
import javax.inject.Inject

/**
 * [UserWithPostViewModel]
 *
 * A ViewModel class that fetches and manages the state of user-post data using the [UserWithPostUseCase].
 * It exposes a `uiState` as a [StateFlow] that represents the current resource state (Loading, Success, or Error).
 *
 * @property getUserWithPostUseCase The use case to fetch and map user-post data.
 */

@HiltViewModel
class UserWithPostViewModel @Inject constructor(
    private val getUserWithPostUseCase: UserWithPostUseCase
) : ViewModel() {

    /**
     * Backing property for the UI state.
     * Emits [Resource.Loading], [Resource.Success], or [Resource.Error] based on data or errors.
     */
    private val _uiState = MutableStateFlow<Resource<List<UserWithPost>>>(Resource.Loading())

    /**
     * Publicly exposed [StateFlow] to observe the state of user-post data.
     */
    val uiState: StateFlow<Resource<List<UserWithPost>>> =
        _uiState.asStateFlow()

    /**
     * Initializes the ViewModel by triggering the fetch process for user-post data.
     */
    init {
        fetchUserWithPosts()
    }

    /**
     * Fetches the user-post data using the [UserWithPostUseCase].
     * Updates [_uiState] based on the success or failure of the operation.
     */
    private fun fetchUserWithPosts() {
        viewModelScope.launch {
            getUserWithPostUseCase().collect { result ->
                _uiState.value = result
            }
        }
    }
}