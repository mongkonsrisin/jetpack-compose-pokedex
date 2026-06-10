package com.pokedex.app

import android.app.Application
import com.pokedex.app.di.networkModule
import com.pokedex.app.di.repositoryModule
import com.pokedex.app.di.useCaseModule
import com.pokedex.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokedexApplication)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}
