package com.dsoft.mytestapplication.util

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object Notifier {
    private val _notificationChannel: MutableSharedFlow<String> = MutableSharedFlow()
    val notificationChannel: SharedFlow<String> = _notificationChannel.asSharedFlow()

    suspend fun sendMessage(message: String) {
        _notificationChannel.emit(message)
    }
}