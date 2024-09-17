package com.example.taskzz.core_data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform