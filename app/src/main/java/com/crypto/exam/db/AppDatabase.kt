package com.crypto.exam.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.crypto.exam.CryptoApplication
import com.crypto.exam.model.CurrencyInfo
import com.crypto.exam.model.CurrencyInfoDao

/**
 * @author Ricky
 * this is room database, get db instance and currency info dao
 */
@Database(entities = [CurrencyInfo::class], version = 1)
@TypeConverters(value = [CurrencyInfoTypeConverter::class])
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "crypto_info.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDB(): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    CryptoApplication.applicationContext(),
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun currencyInfoDao(): CurrencyInfoDao
}