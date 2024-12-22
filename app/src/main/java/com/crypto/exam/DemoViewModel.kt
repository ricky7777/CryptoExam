package com.crypto.exam

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.exam.model.CurrencyInfo
import com.crypto.exam.repository.CurrencyInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * @author Ricky
 * View Model, control all logic
 */
class DemoViewModel(private val repository: CurrencyInfoRepository) : ViewModel(), KoinComponent {
    private val context: Context by inject()
    private val _cryptoList = MutableStateFlow<List<CurrencyInfo>>(emptyList())
    val cryptoList: StateFlow<List<CurrencyInfo>> get() = _cryptoList
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun clearCurrencyInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearCurrencyInfo()
            _cryptoList.value = emptyList()
        }
    }

    fun insertAllCurrencyInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAllCurrencyInfo()
        }
    }

    fun showCurrencyInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _cryptoList.value = repository.getCurrencyInfo()
            Log.i("", "")
        }
    }

    fun showCryptoInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _cryptoList.value = repository.getCryptoInfo()
        }
    }

    fun showFiatInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _cryptoList.value = repository.getFiatInfo()
        }
    }

    fun performActionWithLoading(action: suspend () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000)
            action()
            _isLoading.value = false
        }
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}