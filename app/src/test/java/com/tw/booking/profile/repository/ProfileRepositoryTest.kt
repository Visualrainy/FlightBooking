package com.tw.booking.profile.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.tw.booking.profile.model.Consumption
import com.tw.booking.profile.model.Flight
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class ProfileRepositoryTest {
    private lateinit var repository: ProfileRepository
    private lateinit var context: Context
    private var cacheRepository = mockk<ProfileCacheRepository>()

    @Before
    fun setUp() {
        System.setProperty("javax.net.ssl.trustStore", "NONE")
        val profileApi = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:1080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfileApi::class.java)
        val remoteRepository = ProfileRemoteRepository(profileApi)

        repository = ProfileRepository(remoteRepository, cacheRepository)
    }

    @Test
    fun should_return_no_consumptions_success_with_specific_id() {
        every { cacheRepository.saveConsumptions("1234", any()) } answers {}
        runBlocking {
            val consumptions = repository.consumptions("1234")
            assertEquals(200, consumptions.code)
            assertEquals(0, consumptions.data?.size)
        }
    }

    @Test
    fun should_return_multi_consumptions_success_with_specific_id() {
        every { cacheRepository.saveConsumptions("123456", any()) } answers {}
        runBlocking {
            val consumptions = repository.consumptions("123456")
            assertEquals(200, consumptions.code)
            assertEquals(3, consumptions.data?.size)
        }
    }

    @Test
    fun should_return_single_consumption_success_with_specific_id() {
        every { cacheRepository.saveConsumptions("12345", any()) } answers {}
        runBlocking {
            val consumptions = repository.consumptions("12345")
            assertEquals(200, consumptions.code)
            assertEquals(1, consumptions.data?.size)
        }
    }

    @Test
    fun should_return_fail_with_invalid_id() {
        every { cacheRepository.saveConsumptions("abc", any()) } answers {}
        runBlocking {
            val consumptions = repository.consumptions("abc")
            assertEquals(10000, consumptions.code)
        }
    }

    @Test
    fun should_return_empty_cache_with_network_exception() {
        context = ApplicationProvider.getApplicationContext()
        cacheRepository = ProfileCacheRepository(context)
        cacheRepository.saveConsumptions("123", emptyList())

        val remoteRepository = mockk<ProfileRemoteRepository>()
        coEvery { remoteRepository.consumptions("123") } throws IOException()

        repository = ProfileRepository(remoteRepository, cacheRepository)

        runBlocking {
            val consumptions = repository.consumptions("123")
            assertEquals(0, consumptions.data?.size)
        }
    }

    @Test
    fun should_return_single_consumption_cache_with_network_exception() {
        context = ApplicationProvider.getApplicationContext()
        cacheRepository = ProfileCacheRepository(context)
        cacheRepository.saveConsumptions("123", listOf(Consumption("", 100, "", Flight("","", "", ""))))

        val remoteRepository = mockk<ProfileRemoteRepository>()
        coEvery { remoteRepository.consumptions("123") } throws IOException()

        repository = ProfileRepository(remoteRepository, cacheRepository)

        runBlocking {
            val consumptions = repository.consumptions("123")
            assertEquals(1, consumptions.data?.size)
        }
    }

    @Test
    fun should_return_multi_consumptions_cache_with_network_exception() {
        context = ApplicationProvider.getApplicationContext()
        cacheRepository = ProfileCacheRepository(context)
        val firstConsumption = Consumption("", 100, "", Flight("", "", "", ""))
        val secondConsumption = Consumption("", 200, "", Flight("", "", "", ""))
        cacheRepository.saveConsumptions("123", listOf(firstConsumption, secondConsumption))

        val remoteRepository = mockk<ProfileRemoteRepository>()
        coEvery { remoteRepository.consumptions("123") } throws IOException()

        repository = ProfileRepository(remoteRepository, cacheRepository)

        runBlocking {
            val consumptions = repository.consumptions("123")
            assertEquals(2, consumptions.data?.size)
        }
    }
}