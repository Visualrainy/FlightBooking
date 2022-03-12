package com.tw.booking.order.repository

import com.tw.booking.order.repository.model.ResponseWrapper
import javax.inject.Inject

class OrderRemoteRepository @Inject constructor(private val orderApi: OrderApi) {
    suspend fun refundTicket(orderId: String, ticketId: String): ResponseWrapper<Any> {
        return orderApi.refundTicket(orderId, ticketId)
    }
}