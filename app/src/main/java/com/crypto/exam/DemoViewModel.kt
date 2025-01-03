package com.crypto.exam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.exam.model.CurrencyInfo
import com.crypto.exam.repository.IRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @author Ricky
 * View Model, control all logic
 */
open class DemoViewModel(private val repository: IRepository) : ViewModel() {
    private val _cryptoList = MutableStateFlow<List<CurrencyInfo>>(emptyList())
    val cryptoList: StateFlow<List<CurrencyInfo>> get() = _cryptoList
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun clearCurrencyInfo() {
        viewModelScope.launch {
            repository.clearCurrencyInfo()
            _cryptoList.value = emptyList()
        }
    }

    fun insertAllCurrencyInfo() {
        viewModelScope.launch {
            repository.insertAllCurrencyInfo()
        }
    }

    fun showCurrencyInfo() {
        viewModelScope.launch {
            _cryptoList.value = repository.getCurrencyInfo()
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

    fun filterAndUpdateCryptoList(query: String) {
        _cryptoList.value = _cryptoList.value.filter { currency ->
            matchingCoin(currency, query)
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

    /**
     * search logic
     * start with query or
     * contain with query or
     * symbol with query
     */
    fun matchingCoin(currency: CurrencyInfo, query: String): Boolean {
        if (query.isBlank()) return true

        val searchTerm = query.lowercase()
        val coinName = currency.name.lowercase()
        val coinSymbol = currency.symbol.lowercase()

        return when {
            coinName.startsWith(searchTerm) -> true
            coinName.contains(" $searchTerm") -> true
            coinSymbol.startsWith(searchTerm) -> true
            else -> false
        }
    }
}