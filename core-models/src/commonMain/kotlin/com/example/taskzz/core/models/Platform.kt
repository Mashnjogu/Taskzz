package com.example.taskzz.core.models

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform