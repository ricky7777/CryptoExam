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
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
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
    @Test
    fun `test clearCurrencyInfo clears list`() = runTest {
        viewModel.clearCurrencyInfo()
        assertEquals(emptyList<CurrencyInfo>(), viewModel.cryptoList.first())
    }

    /**
     * test matchingCoin logic
     */
    @Test
    fun `test matchingCoin calls repository`() {
        val mockCurrency = CurrencyInfo("Bitcoin", "BTC", "", "", "")
        val query = "BT"
        `when`(repository.matchingCoin(mockCurrency, query)).thenReturn(true)

        val result = viewModel.matchingCoin(mockCurrency, query)

        assertEquals(true, result)
        verify(repository).matchingCoin(mockCurrency, query)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}