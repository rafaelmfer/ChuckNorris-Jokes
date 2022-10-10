package mobi.pulsus.challenge

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import mobi.pulsus.challenge.commons.extensions.onSingleClick
import mobi.pulsus.challenge.commons.extensions.viewBinding
import mobi.pulsus.challenge.databinding.ActivityMainBinding
import mobi.pulsus.challenge.randomjoke.presentation.RandomJokeActivity
import mobi.pulsus.challenge.randomjokebycategory.presentation.RandomJokeCategoryActivity
import mobi.pulsus.challenge.searchjokes.presentation.SearchJokesActivity

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.onViewCreated()
    }

    private fun ActivityMainBinding.onViewCreated() {
        mbtNightMode.onSingleClick {
            when (this@MainActivity.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                Configuration.UI_MODE_NIGHT_NO -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
        }

        mcvHomeRandomJoke.onSingleClick {
            startActivity(Intent(this@MainActivity, RandomJokeActivity::class.java))
        }

        mcvHomeRandomJokeByCategory.onSingleClick {
            startActivity(Intent(this@MainActivity, RandomJokeCategoryActivity::class.java))
        }

        mcvHomeSearchJokes.onSingleClick {
            startActivity(Intent(this@MainActivity, SearchJokesActivity::class.java))
        }
    }
}