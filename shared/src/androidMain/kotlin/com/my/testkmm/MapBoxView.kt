package com.my.testkmm

import android.content.Context
import com.mapbox.maps.MapView


actual class MapBoxView actual constructor(context: Any) {
    private val mapView: MapView = MapView(context as Context)

    actual fun loadStyle(style: String) {
        mapView.getMapboxMap().loadStyleUri(style)
    }

    actual fun onDestroy() {
        mapView.onDestroy()
    }

    fun getView(): MapView {
        return mapView
    }
}