package mobi.pulsus.challenge.searchjokes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobi.pulsus.challenge.domain.model.SearchJokeModel
import mobi.pulsus.challenge.domain.repository.IChuckNorrisRepository

class SearchJokesViewModel(
    private val repository: IChuckNorrisRepository
) : ViewModel() {

    private val searchJokesMutableLiveData = MutableLiveData<SearchJokeModel>()
    val searchJokesLiveData: LiveData<SearchJokeModel> = searchJokesMutableLiveData

    fun getJokes(keyword: String) {
        viewModelScope.launch {
            try {
                val result = repository.getJokesFromAKeyword(keyword)
                searchJokesMutableLiveData.postValue(result)

            } catch (_: Exception) {
            }
        }
    }
}
