package com.example.quitter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quitter.ui.theme.QuitterTheme

@Composable
fun AchievementsPage(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Achievements grid
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            modifier = modifier.weight(4f)
        ) {
            items(18) {
                AchievementItem()
            }
        }
        GoBackButton(modifier.weight(1f), navController)
    }
}


@Composable
fun AchievementItem() {
    // Placeholder for an achievement item
    Surface(
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        color = colorResource(id = R.color.gray)
    ) {
        // Content of each achievement item here
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AchievementsPagePreview() {
    QuitterTheme {
        AchievementsPage(navController = rememberNavController())
    }
}