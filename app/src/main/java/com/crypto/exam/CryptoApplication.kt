package com.crypto.exam

import android.app.Application
import androidx.room.Room
import com.crypto.exam.db.AppDatabase
import com.crypto.exam.manager.AssetManager
import com.crypto.exam.repository.CurrencyInfoRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 *  @author Ricky
 *  application setting with koin
 *  include AssetManager/CurrencyInfoRepository/DemoViewModel and RoomDB
 */
class CryptoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CryptoApplication)
            modules(appModule)
        }
    }

    private val appModule: Module = module {
        single { AssetManager(get()) }
        single { CurrencyInfoRepository() }
        viewModelOf(::DemoViewModel)
        single<AppDatabase> {
            Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java,
                AppDatabase.DB_NAME
            ).build()
        }
    }
}