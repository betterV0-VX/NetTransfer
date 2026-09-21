package com.example.nettransferdemo

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

private const val TAG = "Utils"

fun createFileForClipboardDataTransfer() {

}

fun fileExists(path: String): Boolean {
    val file = File(path)
    return file.exists() && file.isFile
}

suspend fun writeClipboardDataToFile(path: String, data: String) = withContext(Dispatchers.IO){
    try {
        val file = File(path)
        FileOutputStream(file).use { outputStream ->
            outputStream.write(data.toByteArray(Charsets.UTF_8))
        }
    } catch (e: Exception){
        Log.e(TAG, e.toString())
        throw e
    }
}
