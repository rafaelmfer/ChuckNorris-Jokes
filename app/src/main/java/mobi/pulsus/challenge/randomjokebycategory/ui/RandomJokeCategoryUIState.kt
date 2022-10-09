package mobi.pulsus.challenge.randomjokebycategory.ui

sealed class RandomJokeCategoryUIState {
    object Loading : RandomJokeCategoryUIState()
    class SuccessCategories(val categories: List<String>) : RandomJokeCategoryUIState()
    object Error : RandomJokeCategoryUIState()
}