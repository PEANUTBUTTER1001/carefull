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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openstudy.carefull.R
import com.openstudy.carefull.common.BottomNavigationBar
import com.openstudy.carefull.ui.theme.CarefullTheme
import com.openstudy.carefull.common.MenuButton
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
            Text(text = "마이페이지",
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
            MenuButton(text = "개인정보 관리", onClick = { })
            RowLine()
            MenuButton(text = "기초(활동)대사량 측정", onClick = { })
            RowLine()
            SwitchMenuButton(text = "알림 설정")
            RowLine()
            MenuButton(text = "작성 글 관리", onClick = { })
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