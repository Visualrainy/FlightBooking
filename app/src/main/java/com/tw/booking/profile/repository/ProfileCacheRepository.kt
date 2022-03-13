package com.tw.booking.profile.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tw.booking.order.repository.model.ResponseWrapper
import com.tw.booking.profile.common.PrefKeys
import com.tw.booking.profile.model.Consumption
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ProfileCacheRepository @Inject constructor(@ApplicationContext val context: Context) {
    companion object {
        const val FILE_KEY = "flight_booking"
    }

    fun getConsumptions(id: String): ResponseWrapper<List<Consumption>> {
        val cache = getSharePreference(context)?.getString("${PrefKeys.CONSUMPTIONS}_$id", null)

        return cache?.let {
            val data = Gson().fromJson(cache, object : TypeToken<List<Consumption>>() {}.type) as? List<Consumption>
            ResponseWrapper(null, null, data)
        } ?: ResponseWrapper(null, null, emptyList())
    }

    fun saveConsumptions(id: String, consumptions: List<Consumption>?) {
        getSharePreference(context)?.edit()
            ?.putString("${PrefKeys.CONSUMPTIONS}_$id", Gson().toJson(consumptions))
            ?.apply()
    }

    private fun getSharePreference(context: Context): SharedPreferences? {
        return context.getSharedPreferences(FILE_KEY, Context.MODE_PRIVATE)
    }

}