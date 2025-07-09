package com.openstudy.carefull.screen.medicine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.openstudy.carefull.viewmodel.MedicineViewModel


@Composable
fun MedicineInfoScreen(
    viewModel: MedicineViewModel = hiltViewModel()
) {
    val selectedItem by viewModel.selectedItem.collectAsState()

    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("성분정보", "효능효과")

    selectedItem?.let { item ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = item.itemName ?: "정보 없음",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 약 사진
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "약 사진 (임시)", color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            when (selectedTab) {
                0 -> {
                    // 성분정보 탭
                    InfoBlock(label = "사용 방법", value = item.usage)
                    InfoBlock(label = "주의 사항", value = item.warning)
                    InfoBlock(label = "상호작용", value = item.interaction)
                    InfoBlock(label = "부작용", value = item.sideEffect)
                    InfoBlock(label = "보관 방법", value = item.storage)
                }
                1 -> {
                    // 효능효과 탭
                    InfoBlock(label = "효능/효과", value = item.efficacy)
                }
            }
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("약 정보를 선택해 주세요.", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
private fun InfoBlock(label: String, value: String?) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value ?: "정보 없음",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 8.dp)
        )
    }
}