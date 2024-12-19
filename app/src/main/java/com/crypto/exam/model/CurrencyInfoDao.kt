package com.crypto.exam.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author Ricky
 * this is dao about currency info of room
 */
@Dao
interface CurrencyInfoDao {
    @Query("Select * from `currency_list`")
    suspend fun getCurrencies(): List<CurrencyInfo>

    @Query("Select * from `currency_list` where type = 'Crypto'")
    suspend fun getCryptoCurrencies(): List<CurrencyInfo>

    @Query("Select * from `currency_list` where type = 'Fiat'")
    suspend fun getFiatCurrencies(): List<CurrencyInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<CurrencyInfo>)

    @Query("DELETE FROM currency_list")
    suspend fun clearAllCurrencyInfo()
}