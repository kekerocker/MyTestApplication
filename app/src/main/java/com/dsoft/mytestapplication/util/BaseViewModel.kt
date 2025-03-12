package com.dsoft.mytestapplication.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    abstract val coroutineExceptionHandler: CoroutineExceptionHandler

    protected suspend fun sendMessage(message: String) {
        Notifier.sendMessage(message)
    }
}