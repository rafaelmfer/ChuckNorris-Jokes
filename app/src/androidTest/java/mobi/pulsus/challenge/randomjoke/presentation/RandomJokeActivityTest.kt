//package mobi.pulsus.challenge.randomjoke.presentation
//
//import android.app.Application
//import android.view.View
//import androidx.test.core.app.ActivityScenario
//import androidx.test.core.app.ApplicationProvider.getApplicationContext
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.IdlingRegistry
//import androidx.test.espresso.action.ViewActions
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.matcher.ViewMatchers.Visibility
//import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
//import androidx.test.espresso.matcher.ViewMatchers.withId
//import androidx.test.espresso.matcher.ViewMatchers.withText
//import androidx.test.ext.junit.rules.ActivityScenarioRule
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.filters.LargeTest
//import mobi.pulsus.challenge.data.local.LocalDB
//import mobi.pulsus.challenge.data.network.HTTPClient
//import mobi.pulsus.challenge.data.remote.api.IChuckNorrisApi
//import mobi.pulsus.challenge.data.repository.ChuckNorrisRepository
//import mobi.pulsus.challenge.domain.repository.IChuckNorrisRepository
//import mobi.pulsus.challenge.utils.EspressoIdlingResource
//import org.hamcrest.Matchers.not
//import org.junit.After
//import mobi.pulsus.challenge.R
//import mobi.pulsus.challenge.presentation.randomjoke.RandomJokeActivity
//import mobi.pulsus.challenge.presentation.randomjoke.RandomJokeViewModel
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.core.context.startKoin
//import org.koin.core.context.stopKoin
//import org.koin.dsl.module
//import org.koin.test.KoinTest
//import org.koin.test.get
//import org.robolectric.annotation.Config
//
//@RunWith(AndroidJUnit4::class)
//@LargeTest
////END TO END test to black box test the app
//class RandomJokeActivityTest : KoinTest {
//
//    private lateinit var repository: IChuckNorrisRepository
//    private lateinit var viewModel: RandomJokeViewModel
//    private lateinit var appContext: Application
//
//    @get:Rule
//    var activityScenarioRule = ActivityScenarioRule(RandomJokeActivity::class.java)
//    private lateinit var decorView: View
//
//    /**
//     * As we use Koin as a Service Locator Library to develop our code, we'll also use Koin to test our code.
//     * at this step we will initialize Koin related code to be able to use it in out testing.
//     */
//    @Before
//    fun init() {
//        stopKoin()//stop the original app koin
//        appContext = getApplicationContext()
//        val myModule = module {
//            single { HTTPClient().create(IChuckNorrisApi::class) }
//            single { LocalDB.createJokeDao(appContext) }
//            single { ChuckNorrisRepository(get(), get()) }
//            viewModel {
//                RandomJokeViewModel(
//                    get() as IChuckNorrisRepository
//                )
//            }
//        }
//        //declare a new koin module
//        startKoin {
//            modules(listOf(myModule))
//        }
//        //Get our real repository
//        repository = get()
//        viewModel = get()
//
//        activityScenarioRule.scenario.onActivity { activity ->
//            decorView = activity.window.decorView
//        }
//    }
//
//    /**
//     * Idling resources tell Espresso that the app is idle or busy. This is needed when operations
//     * are not scheduled in the main Looper (for example when executed on a different thread).
//     */
//    @Before
//    fun registerIdlingResource() {
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
////        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
//    }
//
//    /**
//     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
//     */
//    @After
//    fun unregisterIdlingResource() {
//        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
////        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
//    }
//
//    @Test
//    @Config
//    fun addingAReminder_andStartReminderDescriptionActivity() {
//        val activityScenario = ActivityScenario.launch(RandomJokeActivity::class.java)
////        dataBindingIdlingResource.monitorActivity(activityScenario)
//
//        // Verify: no data is shown
//        onView(withId(R.id.tv_category)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
//        onView(withId(R.id.tv_random_joke_creation)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
//        onView(withId(R.id.tv_joke_text)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
//        onView(withId(R.id.mbt_random_joke_new_joke)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
//        onView(withId(R.id.mbt_random_joke_share)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
//
//        // Click add new task
//        onView(withId(R.id.mbt_random_joke_new_joke)).perform(ViewActions.click())
//
//        // Verify: no data is shown
//        onView(withId(R.id.tv_joke_text)).check(matches(not(withText(""))))
//
//        // Make sure the activity is closed before resetting the db:
//        activityScenario.close()
//    }
//}