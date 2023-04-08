package com.example.changeablerecycler.utils

import android.app.Application
import com.example.changeablerecycler.di.appModule
import org.koin.core.context.startKoin

class ItemApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}