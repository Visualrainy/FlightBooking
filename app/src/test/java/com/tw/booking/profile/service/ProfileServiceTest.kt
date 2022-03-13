package com.tw.booking.profile.service

import com.tw.booking.order.repository.model.ResponseWrapper
import com.tw.booking.profile.model.ConsumptionsStatus
import com.tw.booking.profile.repository.ProfileRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProfileServiceTest {

    private lateinit var profileService: ProfileService

    @MockK
    private lateinit var profileRepository: ProfileRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        profileService = ProfileService(profileRepository)
    }

    @Test
    fun should_return_empty_when_id_is_1234() {
        coEvery { profileRepository.consumptions("1234") } returns ResponseWrapper(200, "success", emptyList())
        runBlocking {
            val result = profileService.consumptions("1234")
            assertEquals(0, result.second?.size)
            assertEquals(ConsumptionsStatus.SUCCESS, result.first)
        }
    }
}