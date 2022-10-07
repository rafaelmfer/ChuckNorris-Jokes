package mobi.pulsus.challenge.data.repository

import mobi.pulsus.challenge.data.remote.api.IChuckNorrisApi
import mobi.pulsus.challenge.domain.model.JokeModel
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
}