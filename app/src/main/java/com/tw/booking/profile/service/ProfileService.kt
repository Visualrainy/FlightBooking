package com.tw.booking.profile.service

import com.tw.booking.profile.model.Consumption
import com.tw.booking.profile.model.ConsumptionsStatus
import com.tw.booking.profile.repository.ProfileRepository
import javax.inject.Inject

class ProfileService @Inject constructor(private val profileRepository: ProfileRepository) {
    suspend fun consumptions(id: String): Pair<ConsumptionsStatus, List<Consumption>?> {
        val result = profileRepository.consumptions(id)
        val status = when (result.code) {
            200 -> ConsumptionsStatus.SUCCESS
            10000 -> ConsumptionsStatus.PARAM_INVALID
            else -> ConsumptionsStatus.FAILURE
        }

        return Pair(status, result.data)
    }
}