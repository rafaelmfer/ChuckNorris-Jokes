package mobi.pulsus.challenge.favorites.presentation

import mobi.pulsus.challenge.domain.model.JokeModel

sealed class FavoriteUIState {
    class Success(val jokes: List<JokeModel>) : FavoriteUIState()
    object Error : FavoriteUIState()
}