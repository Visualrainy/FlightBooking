package com.tw.booking.order.service

import com.tw.booking.order.model.RefundTicketStatus
import com.tw.booking.order.repository.OrderRepository
import javax.inject.Inject

class OrderService @Inject constructor(private val orderRepository: OrderRepository) {
    suspend fun refundTicket(orderId: String, ticketId: String): RefundTicketStatus {
        val result = orderRepository.refundTicket(orderId, ticketId)
        return if (result.code == 200) RefundTicketStatus.SUCCESS else RefundTicketStatus.FAILURE
    }
}