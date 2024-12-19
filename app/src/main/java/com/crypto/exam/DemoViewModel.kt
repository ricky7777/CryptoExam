package com.crypto.exam

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.exam.repository.CurrencyInfoRepository
import kotlinx.coroutines.launch

/**
 * @author Ricky
 * View Model, control all logic
 */
class DemoViewModel : ViewModel() {
    private val repository = CurrencyInfoRepository()
    fun clearCurrencyInfo() {
        viewModelScope.launch {
            repository.clearCurrencyInfo()
        }
    }

    fun insertAllCurrencyInfo() {
        viewModelScope.launch {
            repository.insertAllCurrencyInfo()
        }
    }

    fun showCurrencyInfo() {
        viewModelScope.launch {
            val currencyInfo = repository.getCurrencyInfo()
            Log.i("","")
        }
    }

    fun showCryptoInfo() {
        viewModelScope.launch {
            repository.getCryptoInfo()
        }
    }

    fun showFiatInfo() {
        viewModelScope.launch {
            repository.getFiatInfo()
        }
    }

}