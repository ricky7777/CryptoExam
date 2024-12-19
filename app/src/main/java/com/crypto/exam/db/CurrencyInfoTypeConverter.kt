package com.crypto.exam.db

import androidx.room.TypeConverter
import com.crypto.exam.model.CurrencyInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * @author Ricky
 * convert json to object
 */
class CurrencyInfoTypeConverter {
    private val gson = Gson()
    private val type: Type = object : TypeToken<List<CurrencyInfo>>() {}.type

    @TypeConverter
    fun jsonToCurrencyInfo(source: String): List<CurrencyInfo> = gson.fromJson(source, type)
}