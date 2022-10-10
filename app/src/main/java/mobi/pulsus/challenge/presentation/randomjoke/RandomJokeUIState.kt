package mobi.pulsus.challenge.presentation.randomjoke

import mobi.pulsus.challenge.domain.model.JokeModel

sealed class RandomJokeUIState {
    object Loading : RandomJokeUIState()
    class Success(val joke: JokeModel) : RandomJokeUIState()
    object Error : RandomJokeUIState()
}