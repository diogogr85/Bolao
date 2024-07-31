package com.sushicode.bolao

import android.app.Application
import com.sushicode.bolao.di.networkModules
import com.sushicode.bolao.di.presentationModules
import com.sushicode.bolao.di.repositoriesModules
import com.sushicode.bolao.di.useCaseModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BolaoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BolaoApplication)
            modules(networkModules, repositoriesModules, useCaseModules, presentationModules)
        }
    }
}