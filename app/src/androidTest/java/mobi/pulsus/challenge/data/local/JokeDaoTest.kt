package mobi.pulsus.challenge.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import mobi.pulsus.challenge.data.dto.JokeDTO
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Unit test the DAO
@SmallTest
class JokeDaoTest {

    private val jokeDTO = JokeDTO(
        createdAt = "20/10/2222",
        iconUrl = "test",
        id = "test_id",
        url = "test.com",
        value = "This is a test joke",
        isFavorite = false
    )
    private lateinit var database: JokesDatabase

    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            JokesDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun saveJokeOnDb_getTheSameJokeFromDb() = runBlocking {
        database.jokeDao().saveJoke(jokeDTO)

        val savedJoke = database.jokeDao().getJokeById(jokeDTO.id)

        MatcherAssert.assertThat(savedJoke as JokeDTO, Matchers.notNullValue())
        MatcherAssert.assertThat(savedJoke.createdAt, `is`(jokeDTO.createdAt))
        MatcherAssert.assertThat(savedJoke.iconUrl, `is`(jokeDTO.iconUrl))
        MatcherAssert.assertThat(savedJoke.id, `is`(jokeDTO.id))
        MatcherAssert.assertThat(savedJoke.url, `is`(jokeDTO.url))
        MatcherAssert.assertThat(savedJoke.value, `is`(jokeDTO.value))
        MatcherAssert.assertThat(savedJoke.isFavorite, `is`(jokeDTO.isFavorite))
    }

    @Test
    fun saveJokeOnDb_getOnlyOneJokeUsingTheFunctionToGetAllJokes() = runBlocking {
        database.jokeDao().saveJoke(jokeDTO)

        val jokeList = database.jokeDao().getJokes()

        MatcherAssert.assertThat(jokeList.count(), `is`(1))
    }

    @Test
    fun saveJokeOnDb_getThisJoke_ThenDeleteThisJoke() = runBlocking {
        database.jokeDao().saveJoke(jokeDTO)

        val jokeList = database.jokeDao().getJokes()

        database.jokeDao().deleteJoke(jokeDTO)

        val updatedJokeList = database.jokeDao().getJokes()

        MatcherAssert.assertThat(jokeList.count(), `is`(1))

        MatcherAssert.assertThat(updatedJokeList.count(), `is`(0))
    }
}