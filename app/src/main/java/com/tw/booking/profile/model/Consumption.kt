package com.tw.booking.profile.model

data class Consumption(val cid: String, val amount: Int, val payTime: String, val flight: Flight)

data class Flight(val number: String, val departure: String , val arrival: String, val departureTime: String)
