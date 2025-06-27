package com.openstudy.carefull.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.openstudy.carefull.R
import com.openstudy.carefull.common.BottomNavigationBar
import com.openstudy.carefull.ui.theme.CarefullTheme


@Composable
fun ExerciseAndDiet(navController: NavController) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopNavigationBar(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index, item ->
                    selectedTabIndex = index
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(R.string.exercise_and_diet)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (selectedTabIndex) {
                0 -> Exercise()
                1 -> Diet()
            }
        }
    }
}

@Composable
fun Exercise() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedExercise by remember { mutableStateOf("") }
    if (showDialog) {
        ExerciseCountDialog(
            exerciseName = selectedExercise,
            onDismiss = { showDialog = false },
            onConfirm = { count ->
                showDialog = false
            }
        )
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = "운동을 선택하세요.",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(24.dp))

            val exercises = listOf(
                "스쿼트",
                "푸쉬업",
                "런지",
                "운동3",
                "운동4",
                "운동5",
                "운동6",
                "운동7",
                "운동8",
                "운동9",
                "운동10",
                "운동11",
                "운동12",
                "운동13"
            )

            exercises.forEach { exercise ->
                Button(
                    onClick = {
                        selectedExercise = exercise
                        showDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(60.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = exercise,
                        style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun ExerciseCountDialog(
    exerciseName: String,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var count by remember { mutableIntStateOf(10) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
        )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row {
                    Spacer(modifier = Modifier.weight(0.5f))
                    Text(
                        text = exerciseName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(0.25f))
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = "닫기",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.Gray,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                }
                Row {
                    Text(
                        text = "$count",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "회",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            if (count > 4) repeat(5) {
                                count--
                            }
                        },
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) { Text("-5",
                        style = MaterialTheme.typography.bodyLarge)}
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = { if (count > 1) count-- },
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("-1",
                            style = MaterialTheme.typography.bodyLarge)
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = { count++ },
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("+1",
                            style = MaterialTheme.typography.bodyLarge)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            repeat(5) {
                                count++
                            }
                        },
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("+5",
                            style = MaterialTheme.typography.bodyLarge)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = MaterialTheme.shapes.large,
                        onClick = { onConfirm(count) }) {
                        Text("확인",
                            style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }
    }
}

@Composable
fun TopNavigationBar(
    selectedTabIndex: Int,                   // (1) 현재 선택된 탭의 인덱스
    onTabSelected: (Int, String) -> Unit     // (2) 탭 선택 시 호출될 콜백
) {
    val navItems = stringArrayResource(id = R.array.exercise_and_diet)
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        edgePadding = 10.dp,
        indicator = {},
        divider = {}
    ) {
        navItems.forEachIndexed { index, item ->
            Tab(
                selected = (selectedTabIndex == index),
                onClick = { onTabSelected(index, item) },
                text = {
                    Text(
                        text = item, style = MaterialTheme.typography.titleMedium
                    )
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Color.Gray
            )
        }
    }
}

@Composable
fun Diet() {
    var addedFoods by remember { mutableStateOf<List<AddedFood>>(emptyList()) }

    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "오늘 식사 내역", style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                MealType.entries.forEach { mealType ->
                    MealSection(
                        mealType = mealType,
                        addedFoods = addedFoods.filter { it.mealType == mealType },
                        onAddClick = {
                        },
                        onRemoveClick = { foodToRemove ->
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MealSection(
    mealType: MealType,
    addedFoods: List<AddedFood>,
    onAddClick: () -> Unit,
    onRemoveClick: (AddedFood) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = mealType.time,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onAddClick) {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "${mealType.time} 음식 추가",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                }
                IconButton(onClick = onAddClick) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "${mealType.time} 음식 추가",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black
            )
            if (addedFoods.isNotEmpty()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    addedFoods.forEach { addedFood ->
                    }
                }
            } else {
                Text(
                    text = "아직 추가된 음식이 없습니다.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

enum class MealType(val time: String) {
    BREAKFAST("아침"),
    LUNCH("점심"),
    DINNER("저녁"),
    SNACK("간식")
}

data class AddedFood(
    val uniqueId: Long = System.currentTimeMillis(),
    val food: Food,
    val mealType: MealType
)

@Preview(showBackground = true)
@Composable
fun ExerciseAndDietPreview() {
    CarefullTheme {
        val navController = rememberNavController()
        ExerciseAndDiet(navController = navController)
    }
}
