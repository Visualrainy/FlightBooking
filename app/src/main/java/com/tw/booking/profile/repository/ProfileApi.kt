package com.tw.booking.profile.repository

import com.tw.booking.order.repository.model.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileApi {
    @GET("/precharge-contracts/{id}/consumptions")
    suspend fun consumptions(@Path("id") id: String): ResponseWrapper<List<Any>>
}