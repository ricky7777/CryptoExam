package com.crypto.exam

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.exam.model.CurrencyInfo
import com.crypto.exam.repository.CurrencyInfoRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author Ricky
 * View Model, control all logic
 */
class DemoViewModel : ViewModel() {
    private val repository = CurrencyInfoRepository()
    private val _cryptoList = MutableStateFlow<List<CurrencyInfo>>(emptyList())
    val cryptoList: StateFlow<List<CurrencyInfo>> get() = _cryptoList

    fun clearCurrencyInfo() {
        viewModelScope.launch {
            repository.clearCurrencyInfo()
            _cryptoList.value = emptyList()
        }
    }

    fun insertAllCurrencyInfo() {
        viewModelScope.launch {
            repository.insertAllCurrencyInfo()
            _cryptoList.value = repository.getCurrencyInfo()
        }
    }

    fun showCurrencyInfo() {
        viewModelScope.launch {
            _cryptoList.value = repository.getCurrencyInfo()
            Log.i("","")
        }
    }

    fun showCryptoInfo() {
        viewModelScope.launch {
            _cryptoList.value = repository.getCryptoInfo()
        }
    }

    fun showFiatInfo() {
        viewModelScope.launch {
            _cryptoList.value = repository.getFiatInfo()
        }
    }

}