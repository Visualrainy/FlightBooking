package com.tw.booking.profile.repository

import com.tw.booking.order.repository.model.ResponseWrapper
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val remote: ProfileRemoteRepository) {
    suspend fun consumptions(id: String): ResponseWrapper<List<Any>> {
        return remote.consumptions(id)
    }
}