package life.league.challenge.kotlin.data.remote.dto.user

/**
 * [UserDTO]
 *
 * A data transfer object (DTO) representing a user retrieved from the API.
 * This class models the structure of the user data returned by the API and is used
 * to deserialize JSON responses into Kotlin objects.
 *
 * @property address The address of the user, represented by the [Address] object.
 * @property avatar The URL of the user's avatar image. Nullable.
 * @property company The company details of the user, represented by the [Company] object.
 * @property email The email address of the user.
 * @property id The unique identifier of the user.
 * @property name The full name of the user.
 * @property phone The phone number of the user.
 * @property username The username of the user. Nullable.
 * @property website The user's website URL.
 */
data class UserDTO(
    val address: Address,
    val avatar: String?,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String?,
    val website: String
)

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)

data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)

data class Geo(
    val lat: String,
    val lng: String
)