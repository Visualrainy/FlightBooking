package com.tw.booking.profile.service

import com.tw.booking.order.repository.model.ResponseWrapper
import com.tw.booking.profile.model.Consumption
import com.tw.booking.profile.model.ConsumptionsStatus
import com.tw.booking.profile.model.Flight
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

    @Test
    fun should_return_multi_consumptions_when_id_is_123456() {
        val firstConsumption = Consumption(
            "111", 1000, "2022-03-13",
            Flight("", "", "", "")
        )
        val secondConsumption = Consumption(
            "222", 1200, "2022-03-16",
            Flight("", "", "", "")
        )

        val consumptions = listOf(firstConsumption, secondConsumption)

        coEvery { profileRepository.consumptions("123456") } returns ResponseWrapper(200, "success", consumptions)
        runBlocking {
            val result = profileService.consumptions("123456")
            assertEquals(2, result.second?.size)
            assertEquals(ConsumptionsStatus.SUCCESS, result.first)
        }
    }

    @Test
    fun should_return_single_consumption_when_id_is_12345() {
        val firstConsumption = Consumption(
            "111", 1000, "2022-03-13",
            Flight("", "", "", "")
        )

        val consumptions = listOf(firstConsumption)

        coEvery { profileRepository.consumptions("12345") } returns ResponseWrapper(200, "success", consumptions)
        runBlocking {
            val result = profileService.consumptions("12345")
            assertEquals(1, result.second?.size)
            assertEquals(ConsumptionsStatus.SUCCESS, result.first)
        }
    }

    @Test
    fun should_return_fail_when_id_is_abc() {
        coEvery { profileRepository.consumptions("abc") } returns ResponseWrapper(10000, "fail", null)
        runBlocking {
            val result = profileService.consumptions("abc")
            assertEquals(ConsumptionsStatus.PARAM_INVALID, result.first)
        }
    }
}