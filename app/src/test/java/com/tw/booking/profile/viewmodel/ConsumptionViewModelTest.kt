package com.tw.booking.profile.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tw.booking.CoroutinesTestRule
import com.tw.booking.profile.model.ConsumptionsStatus
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
        viewModel = ConsumptionViewModel()
        viewModel.profileService = profileService
    }

    @ExperimentalCoroutinesApi
    @Test
    fun should_return_empty_consumption_when_id_1234() {
        coEvery { profileService.consumptions(any()) } returns Pair<ConsumptionsStatus, List<Any>?>(
            ConsumptionsStatus.SUCCESS,
            emptyList()
        )

        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.fetchConsumptions("1234")
            assertEquals(ConsumptionsStatus.SUCCESS, viewModel.consumptions.value?.first)
            assertEquals(0, viewModel.consumptions.value?.second?.size)
        }
    }
}