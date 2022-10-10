package mobi.pulsus.challenge.favorites.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mobi.pulsus.challenge.commons.extensions.gone
import mobi.pulsus.challenge.commons.extensions.viewBinding
import mobi.pulsus.challenge.commons.extensions.visible
import mobi.pulsus.challenge.databinding.ActivityFavoritesBinding
import mobi.pulsus.challenge.searchjokes.presentation.JokeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesActivity : AppCompatActivity() {

    private val viewModel by viewModel<FavoritesViewModel>()
    private val binding by viewBinding(ActivityFavoritesBinding::inflate)

    private val jokeAdapter = JokeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.onViewCreated()
    }

    private fun ActivityFavoritesBinding.onViewCreated() {
        observables()
    }

    private fun ActivityFavoritesBinding.observables() {
        viewModel.favoriteUIStateLiveData.observe(this@FavoritesActivity) {
            handlerUIState(it)
        }
    }

    private fun ActivityFavoritesBinding.handlerUIState(state: FavoriteUIState) {
        when (state) {
            is FavoriteUIState.Success -> {
                tvFavoritesEmpty.gone
                jokeAdapter.apply {
                    updateJokeList(state.jokes)
                    setFavoriteAction {
                        viewModel.saveOrDeleteJoke(it)
                    }
                }
                rvFavorites.apply {
                    adapter = jokeAdapter
                    visible
                }
            }
            is FavoriteUIState.Error -> {
                tvFavoritesEmpty.visible
                rvFavorites.gone
            }
        }
    }
}