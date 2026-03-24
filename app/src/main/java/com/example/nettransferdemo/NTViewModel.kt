package com.example.nettransferdemo

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.nettransferdemo.data.NTUiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.security.MessageDigest
import java.math.BigInteger

class NTViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NTUiState())

    val uiState: StateFlow<NTUiState> = _uiState.asStateFlow()

    private fun getSha256(text: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(text.toByteArray())
        val hash = BigInteger(1, bytes).toString(16).padStart(64, '0')
        return hash
    }

}