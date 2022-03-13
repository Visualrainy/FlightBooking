package com.tw.booking.profile.service

import com.tw.booking.order.repository.model.ResponseWrapper
import com.tw.booking.profile.repository.ProfileRepository

class ProfileService(private val profileRepository: ProfileRepository) {
    suspend fun consumptions(id: String): ResponseWrapper<List<Any>> {
        return profileRepository.consumptions(id)
    }
}