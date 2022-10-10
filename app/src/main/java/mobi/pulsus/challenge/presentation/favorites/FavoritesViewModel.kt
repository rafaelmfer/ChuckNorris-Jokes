package mobi.pulsus.challenge.presentation.favorites

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobi.pulsus.challenge.domain.model.JokeModel
import mobi.pulsus.challenge.domain.repository.IChuckNorrisRepository

class FavoritesViewModel(
    private val repository: IChuckNorrisRepository
) : ViewModel() {

    private val favoriteUIStateMutableLiveData = MutableLiveData<FavoriteUIState>()
    val favoriteUIStateLiveData: LiveData<FavoriteUIState> = favoriteUIStateMutableLiveData

    init {
        getAllSavedJokes()
    }

    @VisibleForTesting
    fun getAllSavedJokes() {
        viewModelScope.launch {
            val jokes = repository.getAllSavedJokes()
            if (jokes.isNotEmpty()) {
                favoriteUIStateMutableLiveData.postValue(FavoriteUIState.Success(jokes))
            } else {
                favoriteUIStateMutableLiveData.postValue(FavoriteUIState.Error)
            }
        }
    }

    fun saveOrDeleteJoke(joke: JokeModel) {
        viewModelScope.launch {
            if (joke.isFavorite) {
                repository.deleteJoke(joke)
            } else {
                repository.saveJoke(joke)
            }
            getAllSavedJokes()
        }
    }
}