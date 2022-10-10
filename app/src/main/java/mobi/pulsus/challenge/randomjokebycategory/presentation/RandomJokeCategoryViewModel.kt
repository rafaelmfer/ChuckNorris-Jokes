package mobi.pulsus.challenge.randomjokebycategory.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobi.pulsus.challenge.domain.model.JokeModel
import mobi.pulsus.challenge.domain.repository.IChuckNorrisRepository

class RandomJokeCategoryViewModel(
    private val repository: IChuckNorrisRepository
) : ViewModel() {

    private val categoriesMutableLiveData = MutableLiveData<RandomJokeCategoryUIState>()
    val categoriesLiveData: LiveData<RandomJokeCategoryUIState> = categoriesMutableLiveData

    private val randomJokeByCategoryMutableLiveData = MutableLiveData<JokeModel>()
    val randomJokeByCategoryLiveData: LiveData<JokeModel> = randomJokeByCategoryMutableLiveData

    init {
        getCategories()
    }

    @VisibleForTesting
    fun getCategories() {
        categoriesMutableLiveData.postValue(RandomJokeCategoryUIState.Loading)
        viewModelScope.launch {
            try {
                val result = repository.getCategories()
                categoriesMutableLiveData.postValue(RandomJokeCategoryUIState.SuccessCategories(result))
            } catch (ex: Exception) {
                categoriesMutableLiveData.postValue(RandomJokeCategoryUIState.Error)
            }
        }
    }

    fun getRandomJokeFromOneCategory(categoryName: String) {
        viewModelScope.launch {
            try {
                val result = repository.getRandomJokeFromCategory(categoryName)
                randomJokeByCategoryMutableLiveData.postValue(result)
            } catch (_: Exception) {
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