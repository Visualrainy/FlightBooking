package com.tw.booking.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.booking.profile.model.ConsumptionsStatus
import com.tw.booking.profile.service.ProfileService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsumptionViewModel @Inject constructor(private val profileService: ProfileService) : ViewModel() {
    private val _consumptions = MutableLiveData<Pair<ConsumptionsStatus, List<Any>?>>()
    val consumptions: LiveData<Pair<ConsumptionsStatus, List<Any>?>> = _consumptions

    fun fetchConsumptions(id: String) {
        viewModelScope.launch {
            _consumptions.postValue(profileService.consumptions(id))
        }
    }
}