package mobi.pulsus.challenge.randomjoke.ui

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

    private val randomJokeMutableLiveData = MutableLiveData<JokeModel>()
    val randomJokeLiveData: LiveData<JokeModel> = randomJokeMutableLiveData

    init {
        getRandomJoke()
    }

    fun getRandomJoke() {
        viewModelScope.launch {
            try {
                val result = repository.getRandomJoke()
                randomJokeMutableLiveData.postValue(result)

            } catch (e: Exception) {
//                randomJokeMutableLiveData.postValue(ArrayList())
            }
        }
    }
}
