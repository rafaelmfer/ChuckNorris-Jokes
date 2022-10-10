package mobi.pulsus.challenge.di

import mobi.pulsus.challenge.data.local.LocalDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {
    val module = module {
        single { LocalDB.createJokeDao(androidContext()) }
    }
}