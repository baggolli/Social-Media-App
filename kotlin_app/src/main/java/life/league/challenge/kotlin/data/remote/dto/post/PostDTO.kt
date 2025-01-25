package life.league.challenge.kotlin.data.remote.dto.post

/**
 * [PostDTO]
 *
 * A data transfer object (DTO) representing a post retrieved from the API.
 * This class models the structure of the post data returned by the API and is used
 * to deserialize JSON responses into Kotlin objects.
 *
 * @property body The content/body of the post.
 * @property id The unique identifier of the post.
 * @property title The title of the post.
 * @property userId The unique identifier of the user who created the post.
 */
data class PostDTO(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)