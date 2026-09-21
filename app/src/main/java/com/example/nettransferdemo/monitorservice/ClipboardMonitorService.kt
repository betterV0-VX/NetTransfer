package com.example.nettransferdemo.monitorservice

import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.nettransferdemo.ClipboardDataManager
import kotlinx.coroutines.*

private val TAG = "ClipboardMonitorService"

class ClipboardMonitorService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var isPolling = false
    private var pollingJob: Job? = null
    private var lastClipboardText: String? = null

    companion object {
        const val ACTION_START = "START"
        const val ACTION_STOP = "STOP"

        fun startService(context: Context) {
            val intent = Intent(context,ClipboardMonitorService::class.java)
            intent.action = ACTION_START
            context.startService(intent)
            Log.d(TAG, "startService called")
        }
        fun stopService(context: Context) {
            val intent = Intent(context,ClipboardMonitorService::class.java)
            intent.action = ACTION_STOP
            context.startService(intent)
            Log.d(TAG, "stopService called")
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service OnCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service onStartCommand ${intent?.action}")

        val action = intent?.action
        if (action == ACTION_START) {
            startMonitoring()
        } else if (action == ACTION_STOP) {
            stopMonitoring()
            stopSelf()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service destroyed")
        stopMonitoring()
        serviceScope.cancel()
    }

    private fun startMonitoring() {
        if (isPolling) return
        isPolling = true

        Log.d(TAG, "Start monitoring clipboard")

        pollingJob = serviceScope.launch {
            while (isPolling) {
                val currentText = getCurrentClipboardData()

                if (currentText.isNotEmpty() && currentText != lastClipboardText){
                    lastClipboardText = currentText
                    Log.i(TAG, "Скопирован текст $currentText")
                    ClipboardDataManager.updateClipboardText(currentText)
                }
                delay(1000)
            }
        }
    }

    private fun stopMonitoring() {
        Log.d(TAG, "Stop monitoring clipboard")
        isPolling = false
        pollingJob?.cancel()
        pollingJob = null
        lastClipboardText = null
    }

    private fun getCurrentClipboardData(): String {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
            ?: return ""

        return try {
            if (!clipboardManager.hasPrimaryClip()) {
                ""
            } else {
                clipboardManager.primaryClip?.getItemAt(0)?.text?.toString() ?: ""
            }
        } catch (e: Exception) {
            Log.e(TAG, "Ошибка при получении текста буфера обмена", e)
            ""
        }
    }




}

