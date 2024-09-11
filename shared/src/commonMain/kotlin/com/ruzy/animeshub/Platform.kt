package com.ruzy.animeshub

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform