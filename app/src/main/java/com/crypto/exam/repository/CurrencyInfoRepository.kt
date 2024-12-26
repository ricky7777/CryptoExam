package com.crypto.exam.repository

import com.crypto.exam.db.AppDatabase
import com.crypto.exam.db.CurrencyInfoTypeConverter
import com.crypto.exam.manager.AssetManager
import com.crypto.exam.model.CurrencyInfo
import com.crypto.exam.model.CurrencyType
import java.io.IOException
import java.io.InputStream

/**
 * @author Ricky
 * this is data provider, use repository pattern
 * integration the data and provider single window to VM
 */
class CurrencyInfoRepository(
    private val assetManager: AssetManager,
    private val database: AppDatabase
) : IRepository {

    companion object {
        private const val FILE_NAME_CURRENCY_INFO_CRYPTO = "currency_list_crypto.json"
        private const val FILE_NAME_CURRENCY_INFO_FIAT = "currency_list_fiat.json"
    }

    override suspend fun getCurrencyInfo(): List<CurrencyInfo> =
        database.currencyInfoDao().getCurrencies()


    /**
     * query crypto info list from db, if no exist, insert then query
     */
    override suspend fun getCryptoInfo(): List<CurrencyInfo> {
        val currencyInfoList = database.currencyInfoDao().getCryptoCurrencies()
        return if (currencyInfoList.isEmpty()) {
            insertCryptoInfoToDB()
            database.currencyInfoDao().getCryptoCurrencies()
        } else {
            currencyInfoList
        }
    }

    /**
     * query fiat info list from db, if no exist, insert then query
     */
    override suspend fun getFiatInfo(): List<CurrencyInfo> {
        val currencyInfoList = database.currencyInfoDao().getFiatCurrencies()
        return if (currencyInfoList.isEmpty()) {
            insertFiatInfoToDB()
            database.currencyInfoDao().getFiatCurrencies()
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
        database.currencyInfoDao().clearAllCurrencyInfo()
    }

    private suspend fun insertCryptoInfoToDB() {
        val list = getCurrencyInfoFromJson(FILE_NAME_CURRENCY_INFO_CRYPTO)
        list?.forEach {
            it.type = CurrencyType.Crypto.toString()
        }

        list?.let {
            database.currencyInfoDao().insert(it)
        }
    }

    private suspend fun insertFiatInfoToDB() {
        val list = getCurrencyInfoFromJson(FILE_NAME_CURRENCY_INFO_FIAT)
        list?.forEach {
            it.type = CurrencyType.Fiat.toString()
        }

        list?.let {
            database.currencyInfoDao().insert(it)
        }
    }

    private fun getCurrencyInfoFromJson(jsonFileName: String): List<CurrencyInfo>? {
        var currencyInfoList: List<CurrencyInfo>? = null
        try {
            assetManager.let {
                val inputStream: InputStream = it.readAssetFile(jsonFileName)
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