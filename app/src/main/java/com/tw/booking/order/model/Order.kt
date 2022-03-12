package com.tw.booking.order.model

data class Order(val id: String, val tickets: List<Ticket>)
data class Ticket(val id: String)
