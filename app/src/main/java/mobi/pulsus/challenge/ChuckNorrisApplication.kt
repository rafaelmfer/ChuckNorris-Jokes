package mobi.pulsus.challenge

import android.app.Application
import mobi.pulsus.challenge.di.NetworkModule
import mobi.pulsus.challenge.di.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ChuckNorrisApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ChuckNorrisApplication)
            modules(
                RepositoryModule.module,
                NetworkModule.module,
            )
        }
    }
}