package mobi.pulsus.challenge.data.repository

import mobi.pulsus.challenge.data.remote.api.IChuckNorrisApi
import mobi.pulsus.challenge.domain.model.JokeModel
import mobi.pulsus.challenge.domain.model.SearchJokeModel
import mobi.pulsus.challenge.domain.repository.IChuckNorrisRepository

class ChuckNorrisRepository(
    private val chuckNorrisWebService: IChuckNorrisApi
) : IChuckNorrisRepository {

    override suspend fun getRandomJoke(): JokeModel {
        val call = chuckNorrisWebService.getRandomJoke()
        return if (call.isSuccessful) {
            call.body()?.let {
                JokeModel(
                    categories = it.categories,
                    createdAt = it.createdAt,
                    iconUrl = it.iconUrl,
                    id = it.id,
                    url = it.url,
                    value = it.value
                )
            } ?: throw Exception()
        } else {
            throw Exception()
        }
    }

    override suspend fun getCategories(): List<String> {
        val call = chuckNorrisWebService.getCategories()
        return if (call.isSuccessful) {
            call.body() ?: throw Exception()
        } else {
            throw Exception()
        }
    }

    override suspend fun getRandomJokeFromCategory(categoryName: String): JokeModel {
        val call = chuckNorrisWebService.getRandomJokeFromCategory(categoryName)
        return if (call.isSuccessful) {
            call.body()?.let {
                JokeModel(
                    categories = it.categories,
                    createdAt = it.createdAt,
                    iconUrl = it.iconUrl,
                    id = it.id,
                    url = it.url,
                    value = it.value
                )
            } ?: throw Exception()
        } else {
            throw Exception()
        }
    }

    override suspend fun getJokesFromAKeyword(keyword: String): SearchJokeModel {
        val call = chuckNorrisWebService.getJokesFromAKeyword(keyword)
        return if (call.isSuccessful) {
            call.body()?.let {
                SearchJokeModel(total = it.total,
                    result = it.result.map { joke ->
                        JokeModel(
                            categories = joke.categories,
                            createdAt = joke.createdAt,
                            iconUrl = joke.iconUrl,
                            id = joke.id,
                            url = joke.url,
                            value = joke.value
                        )
                    }
                )
            } ?: throw Exception()
        } else {
            throw Exception()
        }
    }
}