package com.tw.booking.profile.service

import com.tw.booking.order.repository.model.ResponseWrapper
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
    fun should_return_status_success_when_code_is_200() {
        coEvery { profileRepository.consumptions("1234") } returns ResponseWrapper(200, "success", emptyList())
        runBlocking {
            val result = profileService.consumptions("1234")
            assertEquals(0, result.data?.size)
            assertEquals(200, result.code)
        }
    }
}