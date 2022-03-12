package com.tw.booking.order.repository

import com.tw.booking.order.repository.model.ResponseWrapper

class OrderRemoteRepository(private val orderApi: OrderApi) {
    suspend fun refundTicket(orderId: String, ticketId: String): ResponseWrapper<Any> {
        return orderApi.refundTicket(orderId, ticketId)
    }
}