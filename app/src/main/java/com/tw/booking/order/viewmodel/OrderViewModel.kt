package com.tw.booking.order.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.booking.order.model.RefundTicketStatus
import com.tw.booking.order.service.OrderService
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderDetailViewModel : ViewModel() {

    @Inject
    lateinit var orderService: OrderService

    private val refundTicketStatus = MutableLiveData<RefundTicketStatus>()
    val _refundTicketStatus: LiveData<RefundTicketStatus> = refundTicketStatus

    fun refundTicket() {
        viewModelScope.launch {
            val refundTicket = orderService.refundTicket("123456", "654321")
            refundTicketStatus.postValue(refundTicket)
        }
    }
}