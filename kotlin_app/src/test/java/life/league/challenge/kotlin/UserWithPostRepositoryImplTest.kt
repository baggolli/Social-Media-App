package life.league.challenge.kotlin

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import life.league.challenge.kotlin.MockData.getMockPostResponse
import life.league.challenge.kotlin.MockData.getMockUserResponse
import life.league.challenge.kotlin.data.remote.ApiService
import life.league.challenge.kotlin.data.remote.dto.post.PostDTO
import life.league.challenge.kotlin.data.remote.dto.user.UserDTO
import life.league.challenge.kotlin.data.repository.UserWithPostRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.assertFailsWith

class UserWithPostRepositoryImplTest {

    @Mock
    private lateinit var mockApiService: ApiService

    private lateinit var repository: UserWithPostRepositoryImpl

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = UserWithPostRepositoryImpl(mockApiService)
    }

    @Test
    fun `getUserDTO should return list of UserDTO from ApiService`() = runTest(testDispatcher) {

        `when`(mockApiService.getUsers()).thenReturn(getMockUserResponse())

        val result = repository.getUserDTO()
        assertEquals(getMockUserResponse(), result)
        verify(mockApiService, times(1)).getUsers()
    }

    @Test
    fun `getPostDTO should return list of PostDTO from ApiService`() = runTest(testDispatcher) {

        `when`(mockApiService.getPosts()).thenReturn(getMockPostResponse())

        val result = repository.getPostDTO()
        assertEquals(getMockPostResponse(), result)
        verify(mockApiService, times(1)).getPosts()
    }
    
    @Test
    fun `getUserDTO should return an empty list when no data is available`() = runTest(testDispatcher) {

        `when`(mockApiService.getUsers()).thenReturn(emptyList())

        val result = repository.getUserDTO()
        assertEquals(emptyList<UserDTO>(), result)
        verify(mockApiService, times(1)).getUsers()
    }

    @Test
    fun `getPostDTO should return an empty list when no data is available`() = runTest(testDispatcher) {

        `when`(mockApiService.getPosts()).thenReturn(emptyList())

        val result = repository.getPostDTO()
        assertEquals(emptyList<PostDTO>(), result)
        verify(mockApiService, times(1)).getPosts()
    }

    @Test
    fun `getUserDTO should throw an exception when API returns null`() = runTest(testDispatcher) {

        `when`(mockApiService.getUsers()).thenThrow(NullPointerException::class.java)

        assertFailsWith<NullPointerException> { repository.getUserDTO() }
        verify(mockApiService, times(1)).getUsers()
    }

    @Test
    fun `getPostDTO should handle null response gracefully`() = runTest(testDispatcher) {

        `when`(mockApiService.getPosts()).thenThrow(NullPointerException::class.java)

        assertFailsWith<NullPointerException> { repository.getPostDTO() }
        verify(mockApiService, times(1)).getPosts()
    }
}
