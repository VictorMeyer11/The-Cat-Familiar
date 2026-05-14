package com.example.thecatfamiliar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform