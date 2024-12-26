package com.crypto.exam

import com.crypto.exam.model.CurrencyInfo
import com.crypto.exam.repository.IRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class DemoViewModelTest {

    private lateinit var repository: IRepository
    private lateinit var viewModel: DemoViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = DemoViewModel(repository)
    }

    /**
     * * test when clear currency info the list is empty
     */
    /**
     * Test when clear currency info the list is empty
     */
    @Test
    fun clearCurrencyInfoClearsList() = runTest {
        viewModel.clearCurrencyInfo()
        assertEquals(emptyList<CurrencyInfo>(), viewModel.cryptoList.first())
    }

    @Test
    fun blankQueryMatchesAll() {
        val currency = CurrencyInfo("BTC", "Bitcoin", "Btc", "", "")
        val query = ""
        assertTrue(viewModel.matchingCoin(currency, query))
    }

    @Test
    fun exactMatchOnName() {
        val currency = CurrencyInfo("BTC", "Bitcoin", "Btc", "", "")
        val query = "Bitcoin"
        assertTrue(viewModel.matchingCoin(currency, query))
    }

    @Test
    fun caseInsensitiveMatchOnName() {
        val currency = CurrencyInfo("BTC", "Bitcoin", "", "", "")
        val query = "bitcoin"
        assertTrue(viewModel.matchingCoin(currency, query))
    }

    @Test
    fun startsWithMatchOnName() {
        val currency = CurrencyInfo("BTC", "Bitcoin", "Btc", "", "")
        val query = "Bit"
        assertTrue(viewModel.matchingCoin(currency, query))
    }

    @Test
    fun partialMatchWithSpaceOnName() {
        val currency = CurrencyInfo("ETC", "Ethereum Classic", "", "", "")
        val query = "Classic"
        assertTrue(viewModel.matchingCoin(currency, query))
    }

    @Test
    fun noPartialMatchWithoutSpaceOnName() {
        val currency = CurrencyInfo("EthereumClassic", "ETC", "", "", "")
        val query = "Classic"
        assertFalse(viewModel.matchingCoin(currency, query))
    }

    @Test
    fun startsWithMatchOnSymbol() {
        val currency = CurrencyInfo("Bitcoin", "BTC", "", "", "")
        val query = "BT"
        assertTrue(viewModel.matchingCoin(currency, query))
    }

    @Test
    fun noMatchWhenQueryDoesNotMatch() {
        val currency = CurrencyInfo("Bitcoin", "BTC", "", "", "")
        val query = "Ethereum"
        assertFalse(viewModel.matchingCoin(currency, query))
    }

    @Test
    fun queryMatchesSymbolCompletely() {
        val currency = CurrencyInfo("Litecoin", "LTC", "", "", "")
        val query = "LTC"
        assertTrue(viewModel.matchingCoin(currency, query))
    }

    @Test
    fun queryDoesNotMatchAnyField() {
        val currency = CurrencyInfo("Litecoin", "LTC", "", "", "")
        val query = "Bit"
        assertFalse(viewModel.matchingCoin(currency, query))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}