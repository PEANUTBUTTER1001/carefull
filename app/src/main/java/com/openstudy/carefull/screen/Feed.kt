package com.openstudy.carefull.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openstudy.carefull.R
import com.openstudy.carefull.common.BottomNavigationBar
import com.openstudy.carefull.ui.theme.CarefullTheme

@Preview(showBackground = true)
@Composable
fun Feed() {
    var isPopUpExpanded by remember { mutableStateOf(false) }
    CarefullTheme {
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        Scaffold(
            topBar = {
                Column(modifier = Modifier.padding(10.dp)) {
                    TopBar(
                        selectedTabIndex = selectedTabIndex,
                        onTabSelected = { index, item ->
                            selectedTabIndex = index
                        }
                    )
                }
            },
            floatingActionButton = {
                if (selectedTabIndex == 0) {
                    SocialFab(
                        isExpanded = isPopUpExpanded,
                        onFabClick = { isPopUpExpanded = !isPopUpExpanded },
                        onCategoryClick = { category ->
                            isPopUpExpanded = false
                        }
                    )
                }
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
                    0 -> Social()
                    1 -> Ranking()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ranking() {

    var selectedGame by remember { mutableStateOf("스쿼트") }
    val gameTypes = listOf("스쿼트", "벤치프레스", "데드리프트", "운동1", "운동2", "운동3", "운동4", "운동5")

//더미 데이터
    val rankList = listOf(
        Rank(101, "김새싹", "스쿼트", 140),
        Rank(102, "이새싹", "벤치프레스", 50),
        Rank(103, "박새싹", "데드리프트", 64),
        Rank(104, "최새싹", "스쿼트", 75),
        Rank(105, "정새싹", "벤치프레스", 90),
        Rank(106, "조새싹", "스쿼트", 110),
        Rank(107, "윤새싹", "데드리프트", 120),
        Rank(108, "강새싹", "스쿼트", 130),
        Rank(109, "신새싹", "벤치프레스", 140),
        Rank(110, "오새싹", "데드리프트", 150),
        Rank(111, "임새싹", "스쿼트", 160),
        Rank(112, "유새싹", "벤치프레스", 170),
        Rank(113, "장새싹", "데드리프트", 180),
        Rank(114, "임새싹", "스쿼트", 190),
        Rank(115, "임새싹", "벤치프레스", 200),
        Rank(116, "임새싹", "데드리프트", 210),
        Rank(117, "가새싹", "스쿼트", 220),
        Rank(118, "나새싹", "벤치프레스", 230),
        Rank(119, "다새싹", "데드리프트", 240),
        Rank(120, "라새싹", "스쿼트", 250),
        Rank(121, "마새싹", "벤치프레스", 260),
        Rank(122, "바새싹", "데드리프트", 270),
        Rank(123, "사새싹", "스쿼트", 280),
        Rank(124, "아새싹", "벤치프레스", 290),
        Rank(125, "자새싹", "데드리프트", 300),
        Rank(126, "차새싹", "스쿼트", 310),
        Rank(127, "카새싹", "벤치프레스", 320),
        Rank(128, "타새싹", "데드리프트", 330),
        Rank(129, "파새싹", "스쿼트", 340),
        Rank(130, "하새싹", "벤치프레스", 350),
        Rank(131, "야새싹", "데드리프트", 360),
        Rank(132, "여새싹", "스쿼트", 370),
        Rank(133, "훈새싹", "벤치프레스", 380),
        Rank(134, "요새싹", "데드리프트", 390),
        Rank(135, "우새싹", "스쿼트", 400),
        Rank(136, "춘새싹", "벤치프레스", 410),
        Rank(137, "예새싹", "데드리프트", 420),
    )

    val filteredList = rankList
        .filter { it.game == selectedGame }
        .sortedByDescending { it.count }


    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(gameTypes) { game ->
                FilterChip(
                    selected = (selectedGame == game),
                    onClick = { selectedGame = game },
                    label = { Text(game) }
                )
            }
        }
        if (filteredList.isNotEmpty()) {
            val topRanker = filteredList.first()
            TopRankerItem(ranker = topRanker)
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 2.dp)

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            itemsIndexed(filteredList) { index, rankItem ->
                RankListItem(
                    rankNumber = index + 1,
                    rankData = rankItem
                )
            }
        }
    }
}

// 본인순위로 나오게 변경해야함
@Composable
fun TopRankerItem(ranker: Rank) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("1위", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "프로필 사진",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = ranker.nickname, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "${ranker.count} 회",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun TopBar(
    selectedTabIndex: Int,                   // (1) 현재 선택된 탭의 인덱스
    onTabSelected: (Int, String) -> Unit     // (2) 탭 선택 시 호출될 콜백
) {
    val navItems = stringArrayResource(id = R.array.feed)
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
fun RankListItem(
    rankNumber: Int,
    rankData: Rank
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$rankNumber",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(40.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = rankData.nickname,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = rankData.game,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Text(
                text = "${rankData.count}회",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Composable
fun Social() {
    var selectedSocial by remember { mutableStateOf("전체") }
    val socialTypes = listOf("전체", "운동", "식단", "병원")

//더미데이터
    val postList = listOf(
        SocialPost(
            1,
            "운동매니아", 1101,
            "오늘 스쿼트 3대 500 찍었습니다!",
            "정말 힘든 하루였지만 보람차네요. 여러분도 득근하세요! #오운완 #헬스타그램",
            12,
            53,
            "운동",
            "2024.05.21"
        ),
        SocialPost(
            2,
            "다이어터",
            1102,
            "식단 공유합니다~",
            "아침: 그릭요거트, 점심: 닭가슴살 샐러드, 저녁: 두부. 배고프지만 참아봅니다.",
            8,
            32,
            "식단",
            "2024.05.21"
        ),
        SocialPost(
            3,
            "헬린이",
            1103,
            "벤치프레스 자세 질문있어요",
            "가슴에 자극이 잘 안 오는데, 팁 좀 알려주실 수 있을까요? 영상 첨부합니다...",
            25,
            15,
            "운동",
            "2024.05.20"
        ),
        SocialPost(
            4,
            "요가사랑",
            1104,
            "아침 요가 루틴",
            "상쾌한 아침을 여는 10분 요가 루틴입니다. 따라해보세요!",
            5,
            41,
            "운동",
            "2024.05.20"
        ),
        SocialPost(5, "허리환자", 1105, "허리근황", "아파요..", 15, 51, "병원", "2024.05.19")
    )

    val filteredList = if (selectedSocial == "전체") {
        postList
    } else {
        postList.filter { it.category == selectedSocial }
    }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(socialTypes) { game ->
                FilterChip(
                    selected = (selectedSocial == game),
                    onClick = { selectedSocial = game },
                    label = { Text(game) }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredList) { post ->
                PostCardItem(
                    post = post,
                    onCardClick = { clickedPost ->
                        {}
                    }
                )
            }
        }
    }
}

@Composable
fun PostCardItem(
    post: SocialPost,
    onCardClick: (Post) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {},
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            // 프로필 정보
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo), // 임시 프로필 이미지
                    contentDescription = "작성자 프로필 사진",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = post.authorNickname, fontWeight = FontWeight.Bold)
                    Text(
                        text = post.date,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 글 제목 및 내용
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3, // 내용이 너무 길면 3줄까지
                overflow = TextOverflow.Ellipsis // ...으로 생략
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 좋아요 및 댓글 수
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = post.category,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(top = 1.dp)
                )
                IconWithText(
                    icon = Icons.Default.FavoriteBorder,
                    text = post.likeCount.toString()
                )
                IconWithText(
                    icon = Icons.AutoMirrored.Filled.Comment,
                    text = post.commentCount.toString()
                )
            }
        }
    }
}

@Composable
private fun SocialFab(
    isExpanded: Boolean,
    onFabClick: () -> Unit,
    onCategoryClick: (String) -> Unit
) {

    Column(horizontalAlignment = Alignment.End) {
        if (isExpanded) {
            val categories = listOf("운동", "식단", "병원")
            categories.forEach { category ->
                ExtendedFloatingActionButton(
                    modifier = Modifier.padding(8.dp),
                    onClick = { onCategoryClick(category) },
                    icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                    text = {
                        Text(
                            category,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                )
            }
        }
        // 메인 FAB
        FloatingActionButton(onClick = onFabClick) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.Close else Icons.Default.Edit,
                contentDescription = "글쓰기"
            )
        }
    }
}

@Composable
private fun IconWithText(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

data class SocialPost(
    val authorId: Int,
    val authorNickname: String,
    val postId: Int,
    val title: String,
    val content: String,
    val commentCount: Int,
    val likeCount: Int,
    val category: String,
    val date: String
)

data class Rank(
    val id: Int,
    val nickname: String,
    val game: String,
    val count: Int
)
