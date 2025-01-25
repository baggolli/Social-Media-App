package life.league.challenge.kotlin.data.repository

import life.league.challenge.kotlin.data.remote.ApiService
import life.league.challenge.kotlin.data.remote.dto.post.PostDTO
import life.league.challenge.kotlin.data.remote.dto.user.UserDTO
import life.league.challenge.kotlin.domain.repository.UserWithPostRepository
import javax.inject.Inject

/**
 * [UserWithPostRepositoryImpl]
 *
 * Implementation of the [UserWithPostRepository] interface.
 * This class is responsible for fetching user and post data from the API.
 *
 * @property apiService The [ApiService] used to make network requests for user and post data.
 */
class UserWithPostRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : UserWithPostRepository {

    /**
     * Fetches a list of users from the API.
     * @return A list of [UserDTO] objects representing user data.
     */
    override suspend fun getUserDTO(): List<UserDTO> {
        return apiService.getUsers()
    }

    /**
     * Fetches a list of posts from the API.
     * @return A list of [PostDTO] objects representing post data.
     */
    override suspend fun getPostDTO(): List<PostDTO> {
        return apiService.getPosts()
    }
}