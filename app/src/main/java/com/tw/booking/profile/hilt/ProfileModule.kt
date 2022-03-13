package com.tw.booking.profile.hilt

import com.tw.booking.profile.repository.ProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ProfileModule {
    @Provides
    fun provideProfileApi(
    ): ProfileApi {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.103:1080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfileApi::class.java)
    }
}