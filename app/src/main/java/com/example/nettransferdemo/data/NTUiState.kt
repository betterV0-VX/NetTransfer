package com.example.nettransferdemo.data

import androidx.compose.material3.BottomAppBarState

data class NTUiState (
    val isTransferTurnedOn: Boolean=false,
    val isUsbConnected: Boolean=false,
    val currentClipboardText: String=""
)