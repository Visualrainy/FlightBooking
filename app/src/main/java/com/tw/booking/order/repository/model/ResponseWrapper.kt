package com.tw.booking.order.repository.model

data class ResponseWrapper<T>(
    val code: Int?,
    val message: String?,
    val data: T?
)