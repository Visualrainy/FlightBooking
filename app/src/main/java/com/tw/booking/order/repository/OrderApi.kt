package com.tw.booking.order.repository

import com.tw.booking.order.repository.model.ResponseWrapper
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {
    @POST("/booking-orders/{oid}/tickets/{tid}/refund")
    suspend fun refundTicket(@Path("oid") oid: String, @Path("tid") tid: String): ResponseWrapper<Any>
}