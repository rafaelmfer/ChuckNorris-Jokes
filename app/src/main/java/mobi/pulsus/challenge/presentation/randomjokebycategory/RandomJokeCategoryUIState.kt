package mobi.pulsus.challenge.presentation.randomjokebycategory

sealed class RandomJokeCategoryUIState {
    object Loading : RandomJokeCategoryUIState()
    class SuccessCategories(val categories: List<String>) : RandomJokeCategoryUIState()
    object Error : RandomJokeCategoryUIState()
}