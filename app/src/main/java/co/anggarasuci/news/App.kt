package co.anggarasuci.news

import android.app.Application
import co.anggarasuci.news.di.appModules
import co.anggarasuci.news.di.domainDataModule
import co.anggarasuci.news.di.networkModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this)
            .build()
        startKoin {
            androidContext(this@App)
            modules(appModules + networkModule + domainDataModule)
        }
    }
}