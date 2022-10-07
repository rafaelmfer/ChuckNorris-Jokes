package mobi.pulsus.challenge.domain.repository

import mobi.pulsus.challenge.domain.model.JokeModel

interface IChuckNorrisRepository {
    suspend fun getRandomJoke() : JokeModel
}