package com.openstudy.carefull.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.openstudy.carefull.R
import com.openstudy.carefull.common.BottomNavigationBar
import com.openstudy.carefull.common.RowLine
import com.openstudy.carefull.ui.theme.CarefullTheme


@Composable
fun Home() {

    Scaffold(
        bottomBar = {
            BottomNavigationBar(R.string.home)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // 스크롤 가능
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = "2025.6",
                fontSize = 20.sp,
                textAlign = TextAlign.Start
            )
            Text(
                text = "일       월       화       수       목       금       토",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "22      23      24      25      26      27      28",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            RowLine()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    CarefullTheme {
        Home()
    }
}