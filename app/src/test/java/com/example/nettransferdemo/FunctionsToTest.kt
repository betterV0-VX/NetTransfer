package com.example.nettransferdemo

import java.math.BigInteger
import java.security.MessageDigest
import org.junit.Test

import org.junit.Assert.*

fun getSha256(text: String): String {
    val bytes = MessageDigest.getInstance("SHA-256").digest(text.toByteArray())
    val hash = BigInteger(1, bytes).toString(16).padStart(64, '0')
    return hash
}

class PlaygroundTests {
    @Test
    fun test_getSha256() {
        val test = getSha256("kdwmkdmwmdw")
        println("hash value = $test")
    }
}
