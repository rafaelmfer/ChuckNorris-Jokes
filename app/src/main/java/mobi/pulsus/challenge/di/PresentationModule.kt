package mobi.pulsus.challenge.di

import mobi.pulsus.challenge.randomjoke.ui.RandomJokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val module = module {
        viewModel { RandomJokeViewModel(get()) }
    }
}