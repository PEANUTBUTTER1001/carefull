package com.openstudy.carefull.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openstudy.carefull.R
import com.openstudy.carefull.common.BottomNavigationBar
import com.openstudy.carefull.common.RowLine
import com.openstudy.carefull.ui.theme.CarefullTheme

@Composable
fun PostWrittenManagement() {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(currentRoute = R.string.mypage)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(R.string.writing_management),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            RowLine()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostWrittenManagementPreview() {
    CarefullTheme {
        PostWrittenManagement()
    }
}