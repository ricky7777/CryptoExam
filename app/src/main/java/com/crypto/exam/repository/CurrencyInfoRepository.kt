package com.crypto.exam.repository

import com.crypto.exam.CryptoApplication
import com.crypto.exam.db.AppDatabase
import com.crypto.exam.db.CurrencyInfoTypeConverter
import com.crypto.exam.model.CurrencyInfo
import com.crypto.exam.model.CurrencyType
import java.io.IOException
import java.io.InputStream

/**
 * @author Ricky
 * this is data provider, use repository pattern
 * integration the data and provider single window to vm
 */
class CurrencyInfoRepository : IRepository {
    companion object {
        private const val FILE_NAME_CURRENCY_INFO_CRYPTO = "currency_list_crypto.json"
        private const val FILE_NAME_CURRENCY_INFO_FIAT = "currency_list_fiat.json"
    }

    override suspend fun getCurrencyInfo(): List<CurrencyInfo> =
        AppDatabase.getDB().currencyInfoDao().getCurrencies()


    /**
     * query crypto info list from db, if no exist, insert then query
     */
    override suspend fun getCryptoInfo(): List<CurrencyInfo> {
        val currencyInfoList = AppDatabase.getDB().currencyInfoDao().getCryptoCurrencies()
        return if (currencyInfoList.isEmpty()) {
            insertCryptoInfoToDB()
            AppDatabase.getDB().currencyInfoDao().getCryptoCurrencies()
        } else {
            currencyInfoList
        }
    }

    /**
     * query fiat info list from db, if no exist, insert then query
     */
    override suspend fun getFiatInfo(): List<CurrencyInfo> {
        val currencyInfoList = AppDatabase.getDB().currencyInfoDao().getFiatCurrencies()
        return if (currencyInfoList.isEmpty()) {
            insertFiatInfoToDB()
            AppDatabase.getDB().currencyInfoDao().getFiatCurrencies()
        } else {
            currencyInfoList
        }
    }

    override suspend fun insertAllCurrencyInfo() {
        clearCurrencyInfo()
        insertCryptoInfoToDB()
        insertFiatInfoToDB()
    }

    override suspend fun clearCurrencyInfo() {
        AppDatabase.getDB().currencyInfoDao().clearAllCurrencyInfo()
    }

    private suspend fun insertCryptoInfoToDB() {
        val list = getCurrencyInfoFromJson(FILE_NAME_CURRENCY_INFO_CRYPTO)
        list?.forEach {
            it.type = CurrencyType.Crypto.toString()
        }

        list?.let {
            AppDatabase.getDB().currencyInfoDao().insert(it)
        }
    }

    private suspend fun insertFiatInfoToDB() {
        val list = getCurrencyInfoFromJson(FILE_NAME_CURRENCY_INFO_FIAT)
        list?.forEach {
            it.type = CurrencyType.Fiat.toString()
        }

        list?.let {
            AppDatabase.getDB().currencyInfoDao().insert(it)
        }
    }

    private fun getCurrencyInfoFromJson(jsonFileName: String): List<CurrencyInfo>? {
        var currencyInfoList: List<CurrencyInfo>? = null
        try {
            CryptoApplication.applicationContext().assets?.let {
                val inputStream: InputStream = it.open(jsonFileName)
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                val source = String(buffer)
                val converter = CurrencyInfoTypeConverter()
                currencyInfoList = converter.jsonToCurrencyInfo(source)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return currencyInfoList
    }
}