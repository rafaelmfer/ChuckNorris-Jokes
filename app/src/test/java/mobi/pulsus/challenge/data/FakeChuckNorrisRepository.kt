package mobi.pulsus.challenge.data

import mobi.pulsus.challenge.domain.model.JokeModel
import mobi.pulsus.challenge.domain.model.SearchJokeModel
import mobi.pulsus.challenge.domain.repository.IChuckNorrisRepository

// Use FakeDataSource that acts as a test double to the LocalDataSource
class FakeChuckNorrisRepository : IChuckNorrisRepository {

    companion object {
        val jokeTest = JokeModel(
            categories = listOf(),
            createdAt = "20/10/2222",
            iconUrl = "test",
            id = "test_id",
            url = "test.com",
            value = "This is a test joke"
        )
        val categoriesTest = listOf("animal", "dev", "food")

        val emptyJokeTest = JokeModel(
            categories = listOf(),
            createdAt = "",
            iconUrl = "",
            id = "",
            url = "",
            value = ""
        )
    }

    private var shouldReturnError = false

    fun shouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getRandomJoke(): JokeModel {
        if (shouldReturnError) {
            throw Exception()
        }

        return jokeTest
    }

    override suspend fun getCategories(): List<String> {
        if (shouldReturnError) {
            throw Exception()
        }

        return categoriesTest
    }

    override suspend fun getRandomJokeFromCategory(categoryName: String): JokeModel {
        if (shouldReturnError) {
            throw Exception()
        }

        return if (categoryName == "") {
            emptyJokeTest
        } else {
            jokeTest
        }
    }

    override suspend fun getJokesFromAKeyword(keyword: String): SearchJokeModel {
        if (shouldReturnError) {
            throw Exception()
        }

        if (keyword.length >= 3) {
            return SearchJokeModel(
                total = 3,
                result = listOf(
                    jokeTest,
                    jokeTest,
                    jokeTest
                )
            )
        } else {
            throw Exception()
        }
    }
}