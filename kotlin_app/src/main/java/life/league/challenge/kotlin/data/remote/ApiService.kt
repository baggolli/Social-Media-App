package life.league.challenge.kotlin.data.remote

import life.league.challenge.kotlin.common.Constants.ACCESS_TOKEN
import life.league.challenge.kotlin.data.remote.dto.post.PostDTO
import life.league.challenge.kotlin.data.remote.dto.user.UserDTO
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * [ApiService]
 *
 * A service interface that defines the API endpoints for fetching user and post data.
 */
interface ApiService {

    /**
     * Fetches a list of users from the API.
     * @return A list of [UserDTO] objects.
     */
    @Headers("x-access-token: $ACCESS_TOKEN")
    @GET("users")
    suspend fun getUsers(): List<UserDTO>

    /**
     * Fetches a list of posts from the API.
     * @return A list of [PostDTO] objects.
     */
    @Headers("x-access-token: $ACCESS_TOKEN")
    @GET("posts")
    suspend fun getPosts(): List<PostDTO>

}