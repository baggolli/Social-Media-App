package life.league.challenge.kotlin.domain.repository

import life.league.challenge.kotlin.data.remote.dto.post.PostDTO
import life.league.challenge.kotlin.data.remote.dto.user.UserDTO

/**
 * [UserWithPostRepository]
 *
 * Interface for fetching user and post data.
 * Defines the contracts for fetching user and post data separately.
 */
interface UserWithPostRepository {

    /**
     * Fetches user data as a list of [UserDTO].
     * @return List of [UserDTO].
     */
    suspend fun getUserDTO(): List<UserDTO>

    /**
     * Fetches post data as a list of [PostDTO].
     * @return List of [PostDTO].
     */
    suspend fun getPostDTO(): List<PostDTO>
}