package com.openstudy.carefull.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openstudy.carefull.R
import com.openstudy.carefull.common.BottomNavigationBar
import com.openstudy.carefull.ui.theme.CarefullTheme
import com.openstudy.carefull.common.MenuButton
import com.openstudy.carefull.common.RowLine
import com.openstudy.carefull.common.SwitchMenuButton

@Composable
fun MyPage() {
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
            Text(text = stringResource(R.string.mypage),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black)

            //임시 프로필 아이콘
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "프로필 사진",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )

            RowLine()
            MenuButton(text = stringResource(R.string.account_management), onClick = { })
            RowLine()
            MenuButton(text = stringResource(R.string.basal_metabolic_rate_measurement), onClick = { })
            RowLine()
            SwitchMenuButton(text = stringResource(R.string.notification_setting))
            RowLine()
            MenuButton(text = stringResource(R.string.writing_management), onClick = { })
            RowLine()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    CarefullTheme {
        MyPage()
    }
}