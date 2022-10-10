package mobi.pulsus.challenge.randomjoke.presentation

import mobi.pulsus.challenge.domain.model.JokeModel

sealed class RandomJokeUIState {
    object Loading : RandomJokeUIState()
    class Success(val joke: JokeModel) : RandomJokeUIState()
    object Error : RandomJokeUIState()
}