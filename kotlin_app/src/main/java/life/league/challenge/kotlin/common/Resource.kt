package life.league.challenge.kotlin.common

/**
 * [Resource]
 *
 * A sealed class representing the state of a resource, such as data being loaded,
 * successfully fetched, or failed due to an error.
 *
 * @param T The type of the data being wrapped.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    /**
     * Represents a loading state.
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)

    /**
     * Represents a successful state with data.
     * @property data The fetched data.
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * Represents an error state.
     * @property message The error message.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}