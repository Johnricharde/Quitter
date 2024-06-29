package com.example.quitter

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quitter.ui.theme.QuitterTheme
import java.util.concurrent.TimeUnit

val LocalBadgesPreferences = staticCompositionLocalOf<SharedPreferences> { error("No preferences provided") }

data class Badge(
    val id: Int,
    val title: String,
    val milestoneMillis: Long
)

@Composable
fun AchievementsPage(navController: NavHostController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("BadgesPrefs", Context.MODE_PRIVATE)
    val badges = listOf(
        Badge(1, "24 Hours", TimeUnit.DAYS.toMillis(1)),
        Badge(2, "48 Hours", TimeUnit.DAYS.toMillis(2)),
        Badge(3, "72 Hours", TimeUnit.DAYS.toMillis(3)),
        Badge(5, "4 Days", TimeUnit.DAYS.toMillis(4)),
        Badge(4, "5 Days", TimeUnit.DAYS.toMillis(5)),
        Badge(6, "6 Days", TimeUnit.DAYS.toMillis(6)),
        Badge(7, "1 Week", TimeUnit.DAYS.toMillis(7)),
        Badge(8, "2 Weeks", TimeUnit.DAYS.toMillis(14)),
        Badge(9, "3 Weeks", TimeUnit.DAYS.toMillis(21)),
        Badge(10, "1 Month", TimeUnit.DAYS.toMillis(30)),
        Badge(11, "2 Months", TimeUnit.DAYS.toMillis(60)),
        Badge(12, "3 Months", TimeUnit.DAYS.toMillis(90)),
        Badge(13, "4 Months", TimeUnit.DAYS.toMillis(120)),
        Badge(14, "5 Months", TimeUnit.DAYS.toMillis(150)),
        Badge(15, "6 Months", TimeUnit.DAYS.toMillis(180)),
        Badge(16, "7 Months", TimeUnit.DAYS.toMillis(210)),
        Badge(17, "8 Months", TimeUnit.DAYS.toMillis(240)),
        Badge(18, "9 Months", TimeUnit.DAYS.toMillis(270)),
        Badge(19, "10 Months", TimeUnit.DAYS.toMillis(300)),
        Badge(20, "11 Months", TimeUnit.DAYS.toMillis(330)),
        Badge(21, "12 Months", TimeUnit.DAYS.toMillis(360)),
    )
    CompositionLocalProvider(LocalBadgesPreferences provides sharedPreferences) {
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
                items(badges.size) { index ->
                    val badge = badges[index]
                    AchievementItem(badge)
                }
            }
            GoBackButton(modifier.weight(1f), navController)
        }
    }
}


@Composable
fun AchievementItem(badge: Badge) {
    val sharedPreferences = LocalBadgesPreferences.current
    val isEarned = sharedPreferences.getBoolean("badge_${badge.id}", false)

    Surface(
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(25.dp),
        color = if (isEarned) colorResource(id = R.color.gold) else colorResource(id = R.color.gray)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = badge.title, fontWeight = FontWeight.Bold)
            if (isEarned) {
                Text(text = "Earned")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AchievementsPagePreview() {
    QuitterTheme {
        AchievementsPage(navController = rememberNavController())
    }
}