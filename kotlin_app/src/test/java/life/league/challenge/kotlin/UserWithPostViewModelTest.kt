@file:OptIn(ExperimentalCoroutinesApi::class)

package life.league.challenge.kotlin

import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import life.league.challenge.kotlin.MockData.userWithPostData
import life.league.challenge.kotlin.common.Resource
import life.league.challenge.kotlin.domain.models.UserWithPost
import life.league.challenge.kotlin.domain.usecase.UserWithPostUseCase
import life.league.challenge.kotlin.presentation.viewmodel.UserWithPostViewModel
import org.junit.After
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.Test
import kotlin.test.assertEquals

class UserWithPostViewModelTest {

    @Mock
    lateinit var mockUseCase: UserWithPostUseCase

    private lateinit var viewModel: UserWithPostViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        `when`(mockUseCase()).thenReturn(flow { emit(Resource.Loading()) })
        viewModel = UserWithPostViewModel(mockUseCase)
    }

    @Test
    fun `uiState should emit Loading state initially`() = runTest {
        advanceUntilIdle()
        val uiState = viewModel.uiState.value
        assertTrue(uiState is Resource.Loading)
    }

    @Test
    fun `uiState should emit Success state with valid data`() = runTest {

        `when`(mockUseCase()).thenReturn(flow { emit(Resource.Success(userWithPostData())) })

        viewModel = UserWithPostViewModel(mockUseCase)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState is Resource.Success)
        assertEquals(userWithPostData(), (uiState as Resource.Success).data)
    }

    @Test
    fun `uiState should emit Error state on HttpException`() = runTest {

        val errorMessage = "An unexpected error occurred"
        `when`(mockUseCase()).thenReturn(flow { emit(Resource.Error(errorMessage)) })

        viewModel = UserWithPostViewModel(mockUseCase)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState is Resource.Error)
        assertEquals(errorMessage, (uiState as Resource.Error).message)
    }

    @Test
    fun `uiState should emit Error state on IOException`() = runTest {
        val errorMessage = "Couldn't reach server. Check your internet connection."
        `when`(mockUseCase()).thenReturn(flow { emit(Resource.Error(errorMessage)) })

        viewModel = UserWithPostViewModel(mockUseCase)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState is Resource.Error)
        assertEquals(errorMessage, (uiState as Resource.Error).message)
    }

    @Test
    fun `uiState should emit Success state with empty data`() = runTest {
        val mockData = emptyList<UserWithPost>()
        `when`(mockUseCase()).thenReturn(flow { emit(Resource.Success(mockData)) })

        viewModel = UserWithPostViewModel(mockUseCase)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertTrue(uiState is Resource.Success)
        assertTrue((uiState as Resource.Success).data?.isEmpty() == true)
    }

    @Test
    fun `uiState should emit Loading for a prolonged duration`() = runTest {
        `when`(mockUseCase()).thenReturn(flow {
            emit(Resource.Loading())
            delay(1000) // Simulate prolonged loading
            emit(Resource.Success(emptyList()))
        })

        viewModel = UserWithPostViewModel(mockUseCase)
        advanceTimeBy(1000)

        val uiState = viewModel.uiState.value
        assertTrue(uiState is Resource.Loading)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}