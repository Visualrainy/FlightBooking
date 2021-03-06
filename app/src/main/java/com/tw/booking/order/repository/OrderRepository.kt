package com.tw.booking.order.repository

import com.tw.booking.order.repository.model.ResponseWrapper
import javax.inject.Inject

class OrderRepository @Inject constructor(private val remote: OrderRemoteRepository) {
    suspend fun refundTicket(orderId: String, ticketId: String): ResponseWrapper<Any> {
        return remote.refundTicket(orderId, ticketId)
    }
}