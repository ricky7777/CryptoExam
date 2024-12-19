package com.crypto.exam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Ricky
 * CurrencyInfo data model
 */
@Entity(tableName = "currency_list")
data class CurrencyInfo(
    @PrimaryKey val id: String,         // The db primary key
    @ColumnInfo val name: String,    // The display name of the currency
    @ColumnInfo val symbol: String,  // The display symbol of the currency
    @ColumnInfo val code: String?,     // Fiat currency code, ISO 4217
    @ColumnInfo var type: String     // currency type Crypto/Fiat
)

enum class CurrencyType {
    Crypto, Fiat
}
