package life.league.challenge.kotlin.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import life.league.challenge.kotlin.common.Resource
import life.league.challenge.kotlin.domain.models.UserWithPost
import life.league.challenge.kotlin.domain.repository.UserWithPostRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * [UserWithPostUseCase]
 *
 * A use case to fetch and map user-post data from the repository.
 * Combines user data and post data into a list of [UserWithPost] objects while filtering invalid entries.
 *
 * @property repository The repository providing access to user and post data.
 */

class UserWithPostUseCase @Inject constructor(
    private val repository: UserWithPostRepository
) {

    /**
     * Executes the use case.
     *
     * Fetches user data and post data, maps them into a list of [UserWithPost],
     * and filters entries where the avatar is empty or username is "Unknown".
     *
     * @return A [Flow] emitting [Resource.Loading], [Resource.Success], or [Resource.Error].
     */
    operator fun invoke(): Flow<Resource<List<UserWithPost>>> = flow {
        try {
            emit(Resource.Loading())

            val userDTOS = repository.getUserDTO()
            val postDTOS = repository.getPostDTO()

            val userWithPost = postDTOS.mapNotNull { post ->
                val user = userDTOS.firstOrNull { it.id == post.id }
                val avatar = user?.avatar ?: ""

                // Only include posts with a valid avatar and non-unknown username
                if (avatar.isNotEmpty() && user?.username != "Unknown") {
                    UserWithPost(
                        userAvatar = avatar,
                        username = user?.username.toString(),
                        title = post.title,
                        description = post.body
                    )
                } else {
                    return@mapNotNull null
                }
            }
            emit(Resource.Success(userWithPost))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}