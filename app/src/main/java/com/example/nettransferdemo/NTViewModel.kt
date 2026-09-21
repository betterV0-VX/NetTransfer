package com.example.nettransferdemo

import android.app.Application
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nettransferdemo.monitorservice.ClipboardMonitorService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.math.BigInteger
import kotlinx.coroutines.Job
import java.security.MessageDigest

class NTViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "NTViewModel"
    private val context = application.applicationContext

//    private val _usbState = MutableStateFlow()
    private val _uiState = MutableStateFlow(NTUiState())
    val uiState: StateFlow<NTUiState> = _uiState.asStateFlow()

    //Состояние буфера обмена
//    private val _clipboardText = MutableStateFlow<String?>(null)
//    val clipboardText: StateFlow<String?> = _clipboardText.asStateFlow()
    val clipboardText: StateFlow<String?> = ClipboardDataManager.clipboardText

    fun setIsTransferTurnedOn(value: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isTransferTurnedOn = value)
        }

        if (value) {
            startClipboardMonitoring()
        } else {
            stopClipboardMonitoring()
        }
        Log.i(TAG, "uiStateValue = ${_uiState.value}")
    }
    private fun startClipboardMonitoring() {
        ClipboardMonitorService.startService(context)
        Log.d(TAG, "Clipboard monitoring started")
    }

    private fun stopClipboardMonitoring() {
        ClipboardMonitorService.stopService(context)
        Log.d(TAG, "Clipboard monitoring stopped")
    }

    private fun getCurrentClipboardText(): String {
        return getCurrentClipboardData()
    }

    override fun onCleared() {
        super.onCleared()
        // Останавливаем сервис при завершении ViewModel
        if (_uiState.value.isTransferTurnedOn) {
            stopClipboardMonitoring()
        }
    }

    // Этот метод дублируется в ClipboardViewModel,
    // рекомендуется вынести в отдельный утилитный класс
    private fun getCurrentClipboardData(): String {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
            ?: return ""

        return try {
            if (!clipboardManager.hasPrimaryClip()) {
                ""
            } else {
                clipboardManager.primaryClip?.getItemAt(0)?.text?.toString() ?: ""
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error getting clipboard data", e)
            ""
        }
    }
    // ? may be optional
    private fun getSha256(text: String): String {
        return try {
            val bytes = MessageDigest.getInstance("SHA-256").digest(text.toByteArray())
            BigInteger(1, bytes).toString(16).padStart(64, '0')
        } catch (e: Exception) {
            Log.e(TAG, "Error generating SHA-256 hash", e)
            ""
        }
    }

}

// Пример data класса NTUiState (добавьте, если его нет)
data class NTUiState(
    val isTransferTurnedOn: Boolean = false,
    val isUsbConnected: Boolean = false,
    // другие поля состояния
)