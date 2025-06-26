package com.openstudy.carefull.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.openstudy.carefull.R

@Composable
fun BottomNavigationBar(currentRoute: String, onTabSelected: (String) -> Unit = {}) {
    val navItems = stringArrayResource(id = R.array.bottom_nav_items)

    Column {
        HorizontalDivider(
            modifier = Modifier,
            thickness = 2.dp,
        )

        NavigationBar {
            navItems.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Image(
                            painter = painterResource(R.drawable.app_logo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp, 30.dp)
                                .clip(RoundedCornerShape(1.dp)),
                            contentScale = ContentScale.Crop
                        )
                    },
                    label = { Text(item) },
                    selected = (currentRoute == item),
                    onClick = { onTabSelected(item) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedTextColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}