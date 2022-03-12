package com.tw.booking.order.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrderRepositoryTest {

    private lateinit var repository: OrderRepository

    @Before
    fun setUp() {
        val orderApi = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:1080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrderApi::class.java)
        val remoteRepository = OrderRemoteRepository(orderApi)

        repository = OrderRepository(remoteRepository)
    }

    @Test
    fun should_return_refund_success_when_ids_is_valid() {
        runBlocking {
            val result = repository.refundTicket("123456", "654321")
            assertEquals(200, result.code)
        }
    }
}