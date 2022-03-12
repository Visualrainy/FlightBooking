package com.tw.booking.order.common.hilt

import com.tw.booking.order.repository.OrderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class OrderModule {
    @Provides
    fun provideOrderApi(
    ): OrderApi {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.103:1080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrderApi::class.java)
    }
}