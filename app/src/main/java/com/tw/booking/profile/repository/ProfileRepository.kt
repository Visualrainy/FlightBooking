package com.tw.booking.profile.repository

import com.tw.booking.order.repository.model.ResponseWrapper
import com.tw.booking.profile.model.Consumption
import java.io.IOException
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val remote: ProfileRemoteRepository,
    private val cache: ProfileCacheRepository
) {
    suspend fun consumptions(id: String): ResponseWrapper<List<Consumption>> {
        return try {
            val consumptions = remote.consumptions(id)
            cache.saveConsumptions(id, consumptions.data)
            consumptions
        } catch (e: IOException) {
            cache.getConsumptions(id)
        }
    }
}