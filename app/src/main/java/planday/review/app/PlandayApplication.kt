package planday.review.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import planday.review.app.di.networkModule
import planday.review.app.di.viewModelsModule
import timber.log.Timber

class PlandayApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@PlandayApplication)
            modules(listOf(viewModelsModule, networkModule))
        }
    }
}