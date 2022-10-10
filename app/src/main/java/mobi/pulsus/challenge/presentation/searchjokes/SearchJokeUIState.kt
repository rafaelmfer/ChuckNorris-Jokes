package mobi.pulsus.challenge.presentation.searchjokes

import mobi.pulsus.challenge.domain.model.JokeModel

sealed class SearchJokeUIState {
    object Loading : SearchJokeUIState()
    class Success(val jokes: List<JokeModel>) : SearchJokeUIState()
    object Error : SearchJokeUIState()
}