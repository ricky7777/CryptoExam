package com.crypto.exam.repository

import com.crypto.exam.model.CurrencyInfo

interface IRepository {
    suspend fun clearCurrencyInfo()
    suspend fun getCurrencyInfo(): List<CurrencyInfo>
    suspend fun getCryptoInfo(): List<CurrencyInfo>
    suspend fun getFiatInfo(): List<CurrencyInfo>
    suspend fun insertAllCurrencyInfo()
}