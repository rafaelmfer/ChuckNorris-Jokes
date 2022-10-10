package mobi.pulsus.challenge.searchjokes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobi.pulsus.challenge.domain.repository.IChuckNorrisRepository

class SearchJokesViewModel(
    private val repository: IChuckNorrisRepository
) : ViewModel() {

    private val searchJokesMutableLiveData = MutableLiveData<SearchJokeUIState>()
    val searchJokesLiveData: LiveData<SearchJokeUIState> get() = searchJokesMutableLiveData

    fun getJokes(keyword: String) {
        searchJokesMutableLiveData.postValue(SearchJokeUIState.Loading)
        viewModelScope.launch {
            try {
                val result = repository.getJokesFromAKeyword(keyword)
                searchJokesMutableLiveData.postValue(SearchJokeUIState.Success(result.result))
            } catch (ex: Exception) {
                searchJokesMutableLiveData.postValue(SearchJokeUIState.Error)
            }
        }
    }
}