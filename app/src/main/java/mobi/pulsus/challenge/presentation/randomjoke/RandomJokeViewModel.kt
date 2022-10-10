package mobi.pulsus.challenge.presentation.randomjoke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobi.pulsus.challenge.domain.model.JokeModel
import mobi.pulsus.challenge.domain.repository.IChuckNorrisRepository

class RandomJokeViewModel(
    private val repository: IChuckNorrisRepository
) : ViewModel() {

    private val randomJokeMutableLiveData = MutableLiveData<RandomJokeUIState>()
    val randomJokeLiveData: LiveData<RandomJokeUIState> = randomJokeMutableLiveData

    init {
        getRandomJoke()
    }

    fun getRandomJoke() {
        randomJokeMutableLiveData.postValue(RandomJokeUIState.Loading)
        viewModelScope.launch {
            try {
                val result = repository.getRandomJoke()
                randomJokeMutableLiveData.postValue(RandomJokeUIState.Success(result))
            } catch (e: Exception) {
                randomJokeMutableLiveData.postValue(RandomJokeUIState.Error)
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
        }
    }
}