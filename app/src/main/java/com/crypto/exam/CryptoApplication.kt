package com.crypto.exam

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 *  @author Ricky
 *  application setting with koin
 */
class CryptoApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: CryptoApplication

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CryptoApplication)
            modules(appModule)
        }
    }

    val appModule: Module = module {
//        single { MyRepository() }
//        single { MyViewModel(get()) }
    }
}