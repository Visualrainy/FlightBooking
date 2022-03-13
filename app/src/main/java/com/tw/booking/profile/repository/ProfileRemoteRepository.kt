package com.tw.booking.profile.repository

import com.tw.booking.order.repository.model.ResponseWrapper
import com.tw.booking.profile.model.Consumption
import javax.inject.Inject

class ProfileRemoteRepository @Inject constructor(private val profileApi: ProfileApi) {
    suspend fun consumptions(id: String): ResponseWrapper<List<Consumption>> {
        return profileApi.consumptions(id)
    }
}