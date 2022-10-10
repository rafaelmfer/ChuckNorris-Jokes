package mobi.pulsus.challenge.presentation.favorites

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import mobi.pulsus.challenge.MainCoroutineRule
import mobi.pulsus.challenge.data.FakeChuckNorrisRepository
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
class FavoritesViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var chuckNorrisRepository: FakeChuckNorrisRepository

    // Class under test
    private lateinit var favoritesViewModel: FavoritesViewModel

    @Before
    fun setUp() {
        chuckNorrisRepository = FakeChuckNorrisRepository()
        favoritesViewModel = FavoritesViewModel(chuckNorrisRepository)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `on load All Saved Jokes _ save 2 jokes`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            chuckNorrisRepository.saveJoke(FakeChuckNorrisRepository.jokeTest)
            chuckNorrisRepository.saveJoke(FakeChuckNorrisRepository.jokeTest)

            //WHEN
            favoritesViewModel.getAllSavedJokes()

            //THEN
            MatcherAssert.assertThat(
                (favoritesViewModel.favoriteUIStateLiveData.value as FavoriteUIState.Success).jokes.size,
                `is`(2)
            )
            MatcherAssert.assertThat(
                (favoritesViewModel.favoriteUIStateLiveData.value as FavoriteUIState.Success).jokes.size,
                `is`(not(3))
            )
        }
    }

    @Test
    fun `on Load All Saved Jokes _ show empty error`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            chuckNorrisRepository.shouldReturnError(true)

            //WHEN
            favoritesViewModel.getAllSavedJokes()

            //THEN
            MatcherAssert.assertThat(favoritesViewModel.favoriteUIStateLiveData.value as FavoriteUIState.Error, `is`(FavoriteUIState.Error))
        }
    }
}