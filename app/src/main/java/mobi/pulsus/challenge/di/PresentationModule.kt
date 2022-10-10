package mobi.pulsus.challenge.di

import mobi.pulsus.challenge.presentation.favorites.FavoritesViewModel
import mobi.pulsus.challenge.presentation.randomjoke.RandomJokeViewModel
import mobi.pulsus.challenge.presentation.randomjokebycategory.RandomJokeCategoryViewModel
import mobi.pulsus.challenge.presentation.searchjokes.SearchJokesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val module = module {
        viewModel { RandomJokeViewModel(get()) }
        viewModel { RandomJokeCategoryViewModel(get()) }
        viewModel { SearchJokesViewModel(get()) }
        viewModel { FavoritesViewModel(get()) }
    }
}