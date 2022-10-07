package mobi.pulsus.challenge.di

import mobi.pulsus.challenge.data.repository.ChuckNorrisRepository
import mobi.pulsus.challenge.domain.repository.IChuckNorrisRepository
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        factory<IChuckNorrisRepository> { ChuckNorrisRepository(get()) }
    }
}