package mobi.pulsus.challenge.data.remote.api

import mobi.pulsus.challenge.data.remote.response.JokeResponse
import retrofit2.Response
import retrofit2.http.GET

interface IChuckNorrisApi {

     @GET("random")
     suspend fun getRandomJoke(): Response<JokeResponse>
}