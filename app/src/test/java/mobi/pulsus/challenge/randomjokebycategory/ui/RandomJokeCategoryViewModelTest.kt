package mobi.pulsus.challenge.randomjokebycategory.ui

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
import org.hamcrest.Matchers.not
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
class RandomJokeCategoryViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var chuckNorrisRepository: FakeChuckNorrisRepository

    // Class under test
    private lateinit var randomJokeCategoryViewModel: RandomJokeCategoryViewModel

    @Before
    fun setUp() {
        chuckNorrisRepository = FakeChuckNorrisRepository()
        randomJokeCategoryViewModel = RandomJokeCategoryViewModel(chuckNorrisRepository)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get Categories _ should return 3 categories`() {
        mainCoroutineRule.runBlockingTest {
            //WHEN
            mainCoroutineRule.pauseDispatcher()
            randomJokeCategoryViewModel.getCategories()

            //THEN
            MatcherAssert.assertThat(randomJokeCategoryViewModel.categoriesLiveData.getOrAwaitValue(), `is`(RandomJokeCategoryUIState.Loading))
            mainCoroutineRule.resumeDispatcher()
            MatcherAssert.assertThat(
                (randomJokeCategoryViewModel.categoriesLiveData.value as RandomJokeCategoryUIState.SuccessCategories).categories.size,
                `is`(3)
            )
            MatcherAssert.assertThat(
                (randomJokeCategoryViewModel.categoriesLiveData.value as RandomJokeCategoryUIState.SuccessCategories).categories.size,
                `is`(not(4))
            )
        }
    }

    @Test
    fun `get Categories _ show error`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            chuckNorrisRepository.shouldReturnError(true)

            //WHEN
            mainCoroutineRule.pauseDispatcher()
            randomJokeCategoryViewModel.getCategories()

            //THEN
            MatcherAssert.assertThat(randomJokeCategoryViewModel.categoriesLiveData.getOrAwaitValue(), `is`(RandomJokeCategoryUIState.Loading))
            mainCoroutineRule.resumeDispatcher()
            MatcherAssert.assertThat(
                (randomJokeCategoryViewModel.categoriesLiveData.value as RandomJokeCategoryUIState.Error),
                `is`(RandomJokeCategoryUIState.Error)
            )
        }
    }

    @Test
    fun `get Random Joke From One Category _ should return a joke`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            val category = "dev"
            val joke = JokeModel(
                categories = listOf(),
                createdAt = "20/10/2222",
                iconUrl = "test",
                id = "test_id",
                url = "test.com",
                value = "This is a test joke"
            )

            //WHEN
            randomJokeCategoryViewModel.getRandomJokeFromOneCategory(category)

            //THEN
            MatcherAssert.assertThat(randomJokeCategoryViewModel.randomJokeByCategoryLiveData.value, `is`(joke))
        }
    }

    @Test
    fun `get Random Joke From an Empty Category _ should return error`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            val category = ""

            //WHEN
            randomJokeCategoryViewModel.getRandomJokeFromOneCategory(category)

            //THEN
            MatcherAssert.assertThat(randomJokeCategoryViewModel.randomJokeByCategoryLiveData.value, `is`(FakeChuckNorrisRepository.emptyJokeTest))
        }
    }
}