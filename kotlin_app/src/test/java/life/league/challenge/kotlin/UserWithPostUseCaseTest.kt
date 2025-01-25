package life.league.challenge.kotlin

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import life.league.challenge.kotlin.MockData.getMockPostResponse
import life.league.challenge.kotlin.MockData.getMockUserResponse
import life.league.challenge.kotlin.MockData.userWithPostData
import life.league.challenge.kotlin.common.Resource
import life.league.challenge.kotlin.domain.models.UserWithPost
import life.league.challenge.kotlin.domain.repository.UserWithPostRepository
import life.league.challenge.kotlin.domain.usecase.UserWithPostUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import java.io.IOException
import kotlin.test.Test

class UserWithPostUseCaseTest {

    @Mock
    private lateinit var userWithPostRepository: UserWithPostRepository

    private lateinit var userWithPostUseCase: UserWithPostUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        userWithPostUseCase = UserWithPostUseCase(repository = userWithPostRepository)
    }

    @Test
    fun `invoke should return success with valid data`() = runTest {

        Mockito.`when`(userWithPostRepository.getUserDTO())
            .thenReturn(getMockUserResponse())
        Mockito.`when`(userWithPostRepository.getPostDTO())
            .thenReturn(getMockPostResponse())

        val response = userWithPostUseCase.invoke().toList()

        assert(response.first() is Resource.Loading)
        assert(response.last() is Resource.Success)

        assertEquals(userWithPostData(), (response.lastOrNull() as Resource.Success).data)
    }

    @Test
    fun `invoke should return success with empty data when API returns no users or posts`() = runTest {

        Mockito.`when`(userWithPostRepository.getUserDTO())
            .thenReturn(emptyList())
        Mockito.`when`(userWithPostRepository.getPostDTO())
            .thenReturn(emptyList())

        val response = userWithPostUseCase.invoke().toList()

        assert(response.first() is Resource.Loading)
        assert(response.last() is Resource.Success)
        assertEquals(emptyList<UserWithPost>(), (response.last() as Resource.Success).data)
    }

    @Test
    fun `invoke should return custom error message for HttpException`() = runTest {

        val mockHttpException = Mockito.mock(HttpException::class.java)
        Mockito.`when`(mockHttpException.localizedMessage).thenReturn("An unexpected error occurred")
        Mockito.`when`(userWithPostRepository.getUserDTO()).thenThrow(mockHttpException)

        val response = userWithPostUseCase.invoke().toList()

        assert(response.first() is Resource.Loading)
        assert(response.last() is Resource.Error)
        assertEquals("An unexpected error occurred", (response.last() as Resource.Error).message)
    }

    @Test
    fun `invoke should return error when IOException occurs`() = runTest {

        Mockito.`when`(userWithPostRepository.getUserDTO())
            .thenAnswer { throw IOException("Couldn't reach server. Check your internet connection.") }

        val response = userWithPostUseCase.invoke().toList()

        assert(response.first() is Resource.Loading)
        assert(response.last() is Resource.Error)
        assertEquals("Couldn't reach server. Check your internet connection.", (response.last() as Resource.Error).message)
    }
}