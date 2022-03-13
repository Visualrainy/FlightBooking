package com.tw.booking.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.booking.profile.model.ConsumptionsStatus
import com.tw.booking.profile.service.ProfileService
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConsumptionViewModel : ViewModel() {

    @Inject
    lateinit var profileService: ProfileService

    private val _consumptions = MutableLiveData<Pair<ConsumptionsStatus, List<Any>?>>()
    val consumptions: LiveData<Pair<ConsumptionsStatus, List<Any>?>> = _consumptions

    fun fetchConsumptions(id: String) {
        viewModelScope.launch {
            _consumptions.postValue(profileService.consumptions(id))
        }
    }
}