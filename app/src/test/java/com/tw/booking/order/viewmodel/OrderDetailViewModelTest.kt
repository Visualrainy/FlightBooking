package com.tw.booking.order.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tw.booking.CoroutinesTestRule
import com.tw.booking.order.model.RefundTicketStatus
import com.tw.booking.order.service.OrderService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OrderDetailViewModelTest {

    private lateinit var viewModel: OrderDetailViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var orderService: OrderService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = OrderDetailViewModel()
        viewModel.orderService = orderService
    }

    @ExperimentalCoroutinesApi
    @Test
    fun should_return_success_status_when_ids_valid() {
        coEvery { orderService.refundTicket(any(), any()) } returns RefundTicketStatus.SUCCESS
        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.refundTicket()
            assertEquals(RefundTicketStatus.SUCCESS, viewModel._refundTicketStatus.value)
        }
    }
}