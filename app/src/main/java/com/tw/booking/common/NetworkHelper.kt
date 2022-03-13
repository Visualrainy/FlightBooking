package com.tw.booking.common

import kotlinx.coroutines.delay
import java.io.IOException

suspend fun <T> retryIO(
    times: Int = Int.MAX_VALUE,
    initialDelay: Long = 100,
    maxDelay: Long = 1000,
    factor: Double = 2.0,
    block: suspend () -> T
): T {
    var currentDelay = initialDelay
    repeat(times) {
        try {
            return block()
        } catch (e: IOException) {
            println("${e.message}")
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    // last attempt
    return block()
}