package com.my.testkmm.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.my.testkmm.MapBoxView

@Composable
fun MapViewContainer() {
    val context = LocalContext.current
    val mapBoxView = remember {
        MapBoxView(context).apply {
            loadStyle(Style.MAPBOX_STREETS)
        }
    }

    AndroidView({ mapBoxView.getView() }) { view: MapView ->
        // You can update your view here.
    }

    DisposableEffect(Unit) {
        onDispose {
            mapBoxView.onDestroy()
        }
    }
}