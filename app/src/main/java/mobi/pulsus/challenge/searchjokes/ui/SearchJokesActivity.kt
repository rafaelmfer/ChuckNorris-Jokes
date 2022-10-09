package mobi.pulsus.challenge.searchjokes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import mobi.pulsus.challenge.commons.extensions.gone
import mobi.pulsus.challenge.commons.extensions.notAllowWhitespace
import mobi.pulsus.challenge.commons.extensions.viewBinding
import mobi.pulsus.challenge.commons.extensions.visible
import mobi.pulsus.challenge.databinding.ActivitySearchJokesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchJokesActivity : AppCompatActivity() {

    private val viewModel by viewModel<SearchJokesViewModel>()
    private val binding by viewBinding(ActivitySearchJokesBinding::inflate)

    private val jokeAdapter = JokeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.onViewCreated()
        binding.gtSearchJokes.setLeftButtonText("<--")
    }

    private fun ActivitySearchJokesBinding.onViewCreated() {
        observables()
        setupSearchBox()
    }

    private fun ActivitySearchJokesBinding.observables() {
        viewModel.run {
            searchJokesLiveData.observe(this@SearchJokesActivity) {
                handlerUIState(it)
            }
        }
    }

    private fun ActivitySearchJokesBinding.handlerUIState(state: SearchJokeUIState) {
        when (state) {
            is SearchJokeUIState.Loading -> {
                pbSearchJokes.visible
                groupSearchJokesStartInstructions.gone
                rvSearchJokes.gone
                tvSearchJokesErrorText.gone
            }
            is SearchJokeUIState.Success -> {
                pbSearchJokes.gone
                tvSearchJokesErrorText.gone
                jokeAdapter.updateJokeList(state.jokes)
                groupSearchJokesStartInstructions.gone
                rvSearchJokes.apply {
                    adapter = jokeAdapter
                    visible
                }
            }
            is SearchJokeUIState.Error -> {
                pbSearchJokes.gone
                rvSearchJokes.gone
                groupSearchJokesStartInstructions.gone
                tvSearchJokesErrorText.visible
            }
        }
    }

    private fun ActivitySearchJokesBinding.setupSearchBox() {
        tieSearchBox.apply {
            notAllowWhitespace()
            doOnTextChanged { text, _, _, _ ->
                text?.let {
                    if (it.length >= 3) {
                        viewModel.getJokes(it.toString())
                    }
                }
            }
        }
    }
}
