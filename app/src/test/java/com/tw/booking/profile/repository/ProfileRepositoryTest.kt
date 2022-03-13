package com.tw.booking.profile.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileRepositoryTest {

    private lateinit var repository: ProfileRepository

    @Before
    fun setUp() {
        val profileApi = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:1080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfileApi::class.java)
        val remoteRepository = ProfileRemoteRepository(profileApi)

        repository = ProfileRepository(remoteRepository)
    }

    @Test
    fun should_return_no_consumptions_success_with_specific_id() {
        runBlocking {
            val consumptions = repository.consumptions("1234")
            assertEquals(0, consumptions.data?.size ?: 1)
        }
    }
}