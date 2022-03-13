package com.tw.booking.profile.repository

import com.tw.booking.order.repository.model.ResponseWrapper
import com.tw.booking.profile.model.Consumption
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val remote: ProfileRemoteRepository) {
    suspend fun consumptions(id: String): ResponseWrapper<List<Consumption>> {
        return remote.consumptions(id)
    }
}