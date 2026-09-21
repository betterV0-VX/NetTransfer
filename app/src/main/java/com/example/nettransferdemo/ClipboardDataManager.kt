package com.example.nettransferdemo

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ClipboardDataManager {
    private val _clipboardText = MutableStateFlow<String?>(null)
    val clipboardText: StateFlow<String?> = _clipboardText.asStateFlow()

    fun updateClipboardText(text: String) {
        _clipboardText.value = text
    }
}