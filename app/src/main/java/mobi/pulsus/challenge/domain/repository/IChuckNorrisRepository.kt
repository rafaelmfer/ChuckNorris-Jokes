package mobi.pulsus.challenge.domain.repository

import mobi.pulsus.challenge.domain.model.JokeModel

interface IChuckNorrisRepository {
    suspend fun getRandomJoke(): JokeModel
    suspend fun getCategories(): List<String>
    suspend fun getRandomJokeFromCategory(categoryName: String): JokeModel
}