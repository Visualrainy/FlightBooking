package com.tw.booking.order.service

import com.tw.booking.order.model.RefundTicketStatus
import com.tw.booking.order.repository.OrderRepository
import com.tw.booking.order.repository.model.ResponseWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OrderServiceTest {
    private lateinit var orderService: OrderService

    @MockK
    private lateinit var orderRepository: OrderRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { orderRepository.refundTicket("123456", "654321") } returns ResponseWrapper(200, "success", null)
        orderService = OrderService(orderRepository)
    }

    @Test
    fun should_return_status_success_when_code_is_200() {
        runBlocking {
            val status = orderService.refundTicket("123456", "654321")
            assertEquals(RefundTicketStatus.SUCCESS, status)
        }
    }
}