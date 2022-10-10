package mobi.pulsus.challenge.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mobi.pulsus.challenge.data.local.JokeDao
import mobi.pulsus.challenge.data.remote.api.IChuckNorrisApi
import mobi.pulsus.challenge.domain.model.JokeModel
import mobi.pulsus.challenge.domain.model.SearchJokeModel
import mobi.pulsus.challenge.domain.repository.IChuckNorrisRepository

class ChuckNorrisRepository(
    private val chuckNorrisWebService: IChuckNorrisApi,
    private val jokeDao: JokeDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IChuckNorrisRepository {

    override suspend fun getRandomJoke(): JokeModel {
        val call = chuckNorrisWebService.getRandomJoke()
        return if (call.isSuccessful) {
            call.body()?.let {
                val isFavorite = getJokeById(it.id) != null
                JokeModel(
                    categories = it.categories,
                    createdAt = it.createdAt,
                    iconUrl = it.iconUrl,
                    id = it.id,
                    url = it.url,
                    value = it.value,
                    isFavorite = isFavorite
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
                val isFavorite = getJokeById(it.id) != null
                JokeModel(
                    categories = it.categories,
                    createdAt = it.createdAt,
                    iconUrl = it.iconUrl,
                    id = it.id,
                    url = it.url,
                    value = it.value,
                    isFavorite = isFavorite
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
                        val isFavorite = getJokeById(joke.id) != null
                        JokeModel(
                            categories = joke.categories,
                            createdAt = joke.createdAt,
                            iconUrl = joke.iconUrl,
                            id = joke.id,
                            url = joke.url,
                            value = joke.value,
                            isFavorite = isFavorite
                        )
                    }
                )
            } ?: throw Exception()
        } else {
            throw Exception()
        }
    }

    override suspend fun getAllSavedJokes(): List<JokeModel> {
        return withContext(ioDispatcher) {
            jokeDao.getJokes().map { joke ->
                joke.dtoToModel()
            }
        }
    }

    override suspend fun getJokeById(id: String): JokeModel? {
        return withContext(ioDispatcher) {
            jokeDao.getJokeById(id)?.dtoToModel()
        }
    }

    override suspend fun saveJoke(jokeModel: JokeModel) {
        withContext(ioDispatcher) {
            jokeModel.isFavorite = true
            jokeDao.saveJoke(jokeModel.modelToDTO())
        }
    }

    override suspend fun deleteJoke(jokeModel: JokeModel) {
        withContext(ioDispatcher) {
            jokeModel.isFavorite = false
            jokeDao.deleteJoke(jokeModel.modelToDTO())
        }
    }
}