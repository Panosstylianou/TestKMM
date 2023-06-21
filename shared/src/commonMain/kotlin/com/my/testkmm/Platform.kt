package com.my.testkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform