package life.league.challenge.kotlin.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import life.league.challenge.kotlin.common.Constants
import life.league.challenge.kotlin.data.remote.ApiService
import life.league.challenge.kotlin.data.repository.UserWithPostRepositoryImpl
import life.league.challenge.kotlin.domain.repository.UserWithPostRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * [AppModule]
 *
 * A Dagger Hilt module responsible for providing application-level dependencies.
 * This module ensures that the required dependencies for the application are created and injected as singletons.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides an instance of [ApiService].
     *
     * Creates a Retrofit instance configured with the base URL and Gson converter factory,
     * and provides the implementation of the [ApiService] interface.
     *
     * @return A singleton instance of [ApiService].
     */
    @Provides
    @Singleton
    fun provideUserProfileApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    /**
     * Provides an instance of [UserWithPostRepository].
     *
     * Binds the [UserWithPostRepositoryImpl] implementation to the [UserWithPostRepository] interface,
     * ensuring a singleton instance is injected wherever the repository is required.
     *
     * @param api The [ApiService] instance required for network operations.
     * @return A singleton instance of [UserWithPostRepositoryImpl].
     */
    @Provides
    @Singleton
    fun provideUserWithProfileRepository(api: ApiService): UserWithPostRepository {
        return UserWithPostRepositoryImpl(api)
    }
}