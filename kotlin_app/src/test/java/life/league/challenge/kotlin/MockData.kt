package life.league.challenge.kotlin

import life.league.challenge.kotlin.data.remote.dto.post.PostDTO
import life.league.challenge.kotlin.data.remote.dto.user.Address
import life.league.challenge.kotlin.data.remote.dto.user.Company
import life.league.challenge.kotlin.data.remote.dto.user.Geo
import life.league.challenge.kotlin.data.remote.dto.user.UserDTO
import life.league.challenge.kotlin.domain.models.UserWithPost

object MockData {

    fun userWithPostData(): List<UserWithPost> {
        return listOf(UserWithPost("avatar_url", "John", "Title 1", "Body 1"))
    }

    fun getMockPostResponse(): List<PostDTO> = listOf(
        PostDTO(id = 1, title = "Title 1", body = "Body 1", userId = 1),
    )

    fun getMockUserResponse(): List<UserDTO> = listOf(
        UserDTO(
            id = 1, username = "John", avatar = "avatar_url",
            address = Address("City", Geo("lat", "long"), "", "suite", "422415"),
            company = Company("bs", "catch phase", "name"),
            phone = "76886451", website = "www.xyz.com", email = "xyz@fmail.com", name = "John"
        ),
    )
}