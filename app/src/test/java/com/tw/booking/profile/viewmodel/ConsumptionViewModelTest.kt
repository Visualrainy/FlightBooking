package com.tw.booking.profile.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tw.booking.CoroutinesTestRule
import com.tw.booking.profile.model.Consumption
import com.tw.booking.profile.model.ConsumptionsStatus
import com.tw.booking.profile.model.Flight
import com.tw.booking.profile.service.ProfileService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ConsumptionViewModelTest {

    private lateinit var viewModel: ConsumptionViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var profileService: ProfileService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = ConsumptionViewModel(profileService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun should_return_empty_consumption_when_id_1234() {
        coEvery { profileService.consumptions("1234") } returns Pair<ConsumptionsStatus, List<Consumption>?>(
            ConsumptionsStatus.SUCCESS,
            emptyList()
        )

        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.fetchConsumptions("1234")
            assertEquals(ConsumptionsStatus.SUCCESS, viewModel.consumptions.value?.first)
            assertEquals(0, viewModel.consumptions.value?.second?.size)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun should_return_multi_consumptions_when_id_123456() {
        val firstConsumption = Consumption(
            "111", 1000, "2022-03-13",
            Flight("", "", "", "")
        )
        val secondConsumption = Consumption(
            "222", 1200, "2022-03-16",
            Flight("", "", "", "")
        )

        val consumptions = listOf(firstConsumption, secondConsumption)
        coEvery { profileService.consumptions("123456") } returns Pair<ConsumptionsStatus, List<Consumption>?>(
            ConsumptionsStatus.SUCCESS,
            consumptions
        )

        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.fetchConsumptions("123456")
            assertEquals(ConsumptionsStatus.SUCCESS, viewModel.consumptions.value?.first)
            assertEquals(2, viewModel.consumptions.value?.second?.size)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun should_return_single_consumption_when_id_12345() {
        val firstConsumption = Consumption(
            "111", 1000, "2022-03-13",
            Flight("", "", "", "")
        )

        val consumptions = listOf(firstConsumption)
        coEvery { profileService.consumptions("12345") } returns Pair<ConsumptionsStatus, List<Consumption>?>(
            ConsumptionsStatus.SUCCESS,
            consumptions
        )

        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.fetchConsumptions("12345")
            assertEquals(ConsumptionsStatus.SUCCESS, viewModel.consumptions.value?.first)
            assertEquals(1, viewModel.consumptions.value?.second?.size)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun should_return_fail_when_id_abc() {
        coEvery { profileService.consumptions("abc") } returns Pair<ConsumptionsStatus, List<Consumption>?>(
            ConsumptionsStatus.PARAM_INVALID,
            null
        )

        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.fetchConsumptions("abc")
            assertEquals(ConsumptionsStatus.PARAM_INVALID, viewModel.consumptions.value?.first)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun should_return_fail_when_status_is_failure() {
        coEvery { profileService.consumptions("123") } returns Pair<ConsumptionsStatus, List<Consumption>?>(
            ConsumptionsStatus.FAILURE,
            null
        )

        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.fetchConsumptions("123")
            assertEquals(ConsumptionsStatus.FAILURE, viewModel.consumptions.value?.first)
        }
    }
}