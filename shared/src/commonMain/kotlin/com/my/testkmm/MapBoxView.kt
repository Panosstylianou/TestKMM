package com.my.testkmm

expect class MapBoxView(context: Any) {
    fun loadStyle(style: String)
    fun onDestroy()
    // Add other methods you need
}
