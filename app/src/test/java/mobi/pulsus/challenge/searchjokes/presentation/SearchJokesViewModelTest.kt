package mobi.pulsus.challenge.searchjokes.presentation

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import kotlinx.coroutines.test.runBlockingTest
import mobi.pulsus.challenge.MainCoroutineRule
import mobi.pulsus.challenge.data.FakeChuckNorrisRepository
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
class SearchJokesViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var chuckNorrisRepository: FakeChuckNorrisRepository

    // Class under test
    private lateinit var searchJokesViewModel: SearchJokesViewModel

    @Before
    fun setUp() {
        chuckNorrisRepository = FakeChuckNorrisRepository()
        searchJokesViewModel = SearchJokesViewModel(chuckNorrisRepository)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get Jokes by a common keyword _ show search with 3 results`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            val keyword = "dev"

            //WHEN
            mainCoroutineRule.pauseDispatcher()
            searchJokesViewModel.getJokes(keyword)

            //THEN
            MatcherAssert.assertThat(searchJokesViewModel.searchJokesLiveData.getOrAwaitValue(), `is`(SearchJokeUIState.Loading))
            mainCoroutineRule.resumeDispatcher()
            MatcherAssert.assertThat(
                (searchJokesViewModel.searchJokesLiveData.value as SearchJokeUIState.Success).jokes,
                `is`(FakeChuckNorrisRepository.searchJokeTest.result)
            )
        }
    }

    @Test
    fun `search Jokes by less than 3 letters keyword _ show empty search`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            val keyword = "al"

            //WHEN
            mainCoroutineRule.pauseDispatcher()
            searchJokesViewModel.getJokes(keyword)

            //THEN
            MatcherAssert.assertThat(searchJokesViewModel.searchJokesLiveData.getOrAwaitValue(), `is`(SearchJokeUIState.Loading))
            mainCoroutineRule.resumeDispatcher()
            MatcherAssert.assertThat(
                (searchJokesViewModel.searchJokesLiveData.value as SearchJokeUIState.Success).jokes,
                `is`(FakeChuckNorrisRepository.emptySearch.result)
            )
        }
    }
}