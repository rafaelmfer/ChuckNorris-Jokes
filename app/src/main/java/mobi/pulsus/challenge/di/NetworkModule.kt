package mobi.pulsus.challenge.di

import mobi.pulsus.challenge.data.network.HTTPClient
import mobi.pulsus.challenge.data.remote.api.IChuckNorrisApi
import org.koin.dsl.module

object NetworkModule {
    val module = module {
        single { HTTPClient() }
        factory { get<HTTPClient>().create(IChuckNorrisApi::class) }
    }
}