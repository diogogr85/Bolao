package com.diogogr85.bolaotungao

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric


val appContext: Context by lazy {
    BolaoTungaoApplication.sAppContext!!
}

class BolaoTungaoApplication: Application() {

    companion object {
        var sAppContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())

        sAppContext = this.applicationContext
    }

}