package mobi.pulsus.challenge.presentation

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import mobi.pulsus.challenge.commons.extensions.onSingleClick
import mobi.pulsus.challenge.commons.extensions.viewBinding
import mobi.pulsus.challenge.databinding.ActivityMainBinding
import mobi.pulsus.challenge.presentation.favorites.FavoritesActivity
import mobi.pulsus.challenge.presentation.randomjoke.RandomJokeActivity
import mobi.pulsus.challenge.presentation.randomjokebycategory.RandomJokeCategoryActivity
import mobi.pulsus.challenge.presentation.searchjokes.SearchJokesActivity

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

        mbtFavorites.onSingleClick {
            startActivity(Intent(this@MainActivity, FavoritesActivity::class.java))
        }
    }
}