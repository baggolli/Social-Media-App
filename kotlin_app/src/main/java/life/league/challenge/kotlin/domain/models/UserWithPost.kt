package life.league.challenge.kotlin.domain.models

/**
 * [UserWithPost]
 *
 * A data class representing the combination of user data and post data.
 *
 * @property userAvatar The avatar of the user (non-empty string for valid entries).
 * @property username The username of the user (must not be "Unknown").
 * @property title The title of the user's post.
 * @property description The body or description of the user's post.
 */
data class UserWithPost(
    val userAvatar: String,
    val username: String,
    val title: String,
    val description: String
)