package mobi.pulsus.challenge.domain.repository

import mobi.pulsus.challenge.domain.model.JokeModel
import mobi.pulsus.challenge.domain.model.SearchJokeModel

interface IChuckNorrisRepository {
    suspend fun getRandomJoke(): JokeModel
    suspend fun getCategories(): List<String>
    suspend fun getRandomJokeFromCategory(categoryName: String): JokeModel
    suspend fun getJokesFromAKeyword(keyword: String): SearchJokeModel
    suspend fun getAllSavedJokes(): List<JokeModel>
    suspend fun getJokeById(id: String): JokeModel?
    suspend fun saveJoke(jokeModel: JokeModel)
    suspend fun deleteJoke(jokeModel: JokeModel)
}