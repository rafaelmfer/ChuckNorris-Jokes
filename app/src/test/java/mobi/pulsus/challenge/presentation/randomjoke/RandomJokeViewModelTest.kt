package mobi.pulsus.challenge.presentation.randomjoke

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import kotlinx.coroutines.test.runBlockingTest
import mobi.pulsus.challenge.MainCoroutineRule
import mobi.pulsus.challenge.data.FakeChuckNorrisRepository
import mobi.pulsus.challenge.domain.model.JokeModel
import mobi.pulsus.challenge.getOrAwaitValue
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class RandomJokeViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var chuckNorrisRepository: FakeChuckNorrisRepository

    // Class under test
    private lateinit var randomJokeViewModel: RandomJokeViewModel

    @Before
    fun setUp() {
        chuckNorrisRepository = FakeChuckNorrisRepository()
        randomJokeViewModel = RandomJokeViewModel(chuckNorrisRepository)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get Random Joke _ check if the joke is not empty`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            val joke = JokeModel(
                categories = listOf(),
                createdAt = "20/10/2222",
                iconUrl = "test",
                id = "test_id",
                url = "test.com",
                value = "This is a test joke"
            )

            //WHEN
            mainCoroutineRule.pauseDispatcher()
            randomJokeViewModel.getRandomJoke()

            //THEN
            MatcherAssert.assertThat(randomJokeViewModel.randomJokeLiveData.getOrAwaitValue(), `is`(RandomJokeUIState.Loading))
            mainCoroutineRule.resumeDispatcher()
            MatcherAssert.assertThat((randomJokeViewModel.randomJokeLiveData.value as RandomJokeUIState.Success).joke, `is`(joke))
        }
    }

    @Test
    fun `get Random Joke _ show error`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            chuckNorrisRepository.shouldReturnError(true)

            //WHEN
            mainCoroutineRule.pauseDispatcher()
            randomJokeViewModel.getRandomJoke()

            //THEN
            MatcherAssert.assertThat(randomJokeViewModel.randomJokeLiveData.getOrAwaitValue(), `is`(RandomJokeUIState.Loading))
            mainCoroutineRule.resumeDispatcher()
            MatcherAssert.assertThat((randomJokeViewModel.randomJokeLiveData.value as RandomJokeUIState.Error), `is`(RandomJokeUIState.Error))
        }
    }
}