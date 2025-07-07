package com.openstudy.carefull.medicineSearch

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.hilt.navigation.compose.hiltViewModel
import com.openstudy.carefull.viewmodel.MedicineViewModel

@Composable
fun MedicineSearchScreen(
    viewModel: MedicineViewModel = hiltViewModel(),
    onNavigateToMedicineInfo: () -> Unit
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val searchResults by viewModel.medicineList.collectAsState()
    val recentSearches = remember { mutableStateListOf<String>() }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 검색 입력창
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFFF5F5F5), RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.PhotoCamera,
                contentDescription = "카메라"
            )

            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("약 이름을 입력하세요") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )

            if (query.text.isNotEmpty()) {
                IconButton(onClick = { query = TextFieldValue("") }) {
                    Icon(imageVector = Icons.Default.Cancel, contentDescription = "지우기")
                }
            }

            IconButton(onClick = {
                val input = query.text.trim()
                if (input.isEmpty()) {
                    Toast.makeText(context, "검색어를 입력하세요", Toast.LENGTH_SHORT).show()
                    return@IconButton
                }

                viewModel.searchMedicine(input)

                if (!recentSearches.contains(input)) {
                    recentSearches.add(0, input)
                    if (recentSearches.size > 10) recentSearches.removeAt(recentSearches.lastIndex)
                }
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "검색")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 최근 검색어 섹션
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("최근 검색어", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "전체 삭제",
                color = Color.Gray,
                modifier = Modifier.clickable {
                    recentSearches.clear()
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (recentSearches.isEmpty()) {
            Text("검색 기록이 없습니다.", color = Color.Gray)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 160.dp)
            ) {
                items(recentSearches) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                query = TextFieldValue(item)
                                viewModel.searchMedicine(item)
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = item)
                        IconButton(onClick = { recentSearches.remove(item) }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "삭제")
                        }
                    }
                    Divider()
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // 검색 결과 리스트
        if (searchResults.isNotEmpty()) {
            Text("검색 결과", style = MaterialTheme.typography.titleMedium)

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(searchResults) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable {
                                viewModel.setSelectedItem(item)
                                onNavigateToMedicineInfo()
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("제품명: ${item.itemName ?: "정보 없음"}", style = MaterialTheme.typography.bodyLarge)
                            Text("효능: ${item.efficacy ?: "정보 없음"}", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}