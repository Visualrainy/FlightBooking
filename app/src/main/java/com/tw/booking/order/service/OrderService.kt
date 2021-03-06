package com.tw.booking.order.service

import com.tw.booking.common.retryIO
import com.tw.booking.order.model.RefundTicketStatus
import com.tw.booking.order.repository.OrderRepository
import java.io.IOException
import javax.inject.Inject

class OrderService @Inject constructor(private val orderRepository: OrderRepository) {
    suspend fun refundTicket(orderId: String, ticketId: String): RefundTicketStatus {
        return try {
            val result = retryIO(times = 3) { orderRepository.refundTicket(orderId, ticketId) }
            when (result.code) {
                200 -> RefundTicketStatus.SUCCESS
                10000, 10001 -> RefundTicketStatus.PARAM_INVALID
                10002 -> RefundTicketStatus.REPEAT_REFUND
                else -> RefundTicketStatus.FAILURE
            }
        } catch (e: IOException) {
            RefundTicketStatus.FAILURE
        }
    }
}