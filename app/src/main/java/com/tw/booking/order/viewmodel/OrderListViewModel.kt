package com.tw.booking.order.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.booking.common.SingleLiveEvent
import com.tw.booking.order.model.Order
import com.tw.booking.order.model.RefundTicketStatus
import com.tw.booking.order.model.Ticket
import com.tw.booking.order.service.OrderService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor() : ViewModel() {
    private val orders = listOf(Order("123456", listOf(Ticket("654321"))))

    @Inject
    lateinit var orderService: OrderService

    private val _refundTicketStatus = SingleLiveEvent<RefundTicketStatus>()
    val refundTicketStatus: LiveData<RefundTicketStatus> = _refundTicketStatus

    fun refundTicket(index: Int) {
        viewModelScope.launch {
            val activeOrder = orders[0]
            val refundTicket = orderService.refundTicket(activeOrder.id, activeOrder.tickets[index].id)
            _refundTicketStatus.postValue(refundTicket)
        }
    }
}