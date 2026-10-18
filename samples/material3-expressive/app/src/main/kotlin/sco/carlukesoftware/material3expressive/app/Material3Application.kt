package sco.carlukesoftware.material3expressive.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Material3Application() : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Material3Application)
            androidLogger()

            modules(

            )
        }
    }
}
