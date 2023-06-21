package com.my.testkmm

import platform.Foundation.NSURL
import platform.UIKit.UIView
import cocoapods.MapboxMaps.*
actual class MapBoxView actual constructor(context: Any) {
    private val mapView: UIView

    init {
        // Create the mapView using the appropriate platform-specific API
        mapView = createMapView()
    }
    actual fun loadStyle(style: String) {
        // Load the style for the mapView
        val url = NSURL(string = style)
        // Implement the logic to apply the style to the mapView
        // ...
    }
    actual fun onDestroy() {
        // Any cleanup if necessary
    }
    private fun createMapView(): UIView {
        // Use the appropriate platform-specific API to create the mapView
        // ...
        return UIView() // Replace with the actual mapView instance
    }
}
