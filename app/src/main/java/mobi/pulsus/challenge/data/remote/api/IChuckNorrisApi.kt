package mobi.pulsus.challenge.data.remote.api

import mobi.pulsus.challenge.data.remote.response.JokeResponse
import mobi.pulsus.challenge.data.remote.response.SearchJokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IChuckNorrisApi {

    @GET("random")
    suspend fun getRandomJoke(): Response<JokeResponse>

    @GET("categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("random")
    suspend fun getRandomJokeFromCategory(
        @Query("category") category: String
    ): Response<JokeResponse>

    @GET("search")
    suspend fun getJokesFromAKeyword(
        @Query("query") keyword: String
    ): Response<SearchJokeResponse>
}