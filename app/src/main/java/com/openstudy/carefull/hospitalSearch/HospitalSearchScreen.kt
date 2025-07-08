package com.openstudy.carefull.hospitalSearch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.MapView
import com.openstudy.carefull.diseaseSearch.DiseaseSearchScreen
import com.openstudy.carefull.ui.theme.CarefullTheme

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