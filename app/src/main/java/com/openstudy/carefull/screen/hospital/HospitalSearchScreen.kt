package com.openstudy.carefull.screen.hospital

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.MapView

@Composable
fun HospitalSearchScreen(
) {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            getMapAsync { naverMap ->
                naverMap.cameraPosition = CameraPosition(
                    LatLng(37.5665, 126.9780),
                    14.0
                )
            }
        }
    }
}