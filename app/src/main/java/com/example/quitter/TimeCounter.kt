package com.example.quitter

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Locale
import java.util.concurrent.TimeUnit

@Composable
fun TimeCounter() {
    var time by remember { mutableLongStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }
    var startTime by remember { mutableLongStateOf(0L) }
    var elapsedTime by remember { mutableLongStateOf(0L) }
    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("TimerPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    val milestones = listOf(
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

    LaunchedEffect(Unit) {
        // Restore previous state from shared preferences
        startTime = sharedPreferences.getLong("startTime", 0L)
        isRunning = sharedPreferences.getBoolean("isRunning", false)
        elapsedTime = sharedPreferences.getLong("elapsedTime", 0L)

        if (isRunning) {
            // Calculate the current elapsed time
            val currentTime = System.currentTimeMillis()
            elapsedTime += currentTime - startTime
            startTime = currentTime
        }

        // Update time based on elapsed time
        time = elapsedTime
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = formatTime(timeMillis = time),
            color = colorResource(id = R.color.white),
            fontSize = 40.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.days),
                color = colorResource(id = R.color.white),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.hours),
                color = colorResource(id = R.color.white),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.minutes),
                color = colorResource(id = R.color.white),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.seconds),
                color = colorResource(id = R.color.white),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        FilledButton(
            onClick = {
                if (isRunning) {
                    // Stop the timer
                    isRunning = false
                    elapsedTime += System.currentTimeMillis() - startTime
                } else {
                    // Start or resume the timer
                    startTime = System.currentTimeMillis()
                    isRunning = true
                }
                editor.putLong("startTime", startTime)
                editor.putBoolean("isRunning", isRunning)
                editor.putLong("elapsedTime", elapsedTime)
                editor.apply()
            },
            text = if (isRunning) stringResource(id = R.string.stop) else stringResource(id = R.string.start)
        )
        FilledButton(
            onClick = {
                // Show confirmation dialog for reset
                showDialog = true
            },
            text = stringResource(id = R.string.reset)
        )
    }

    // Reset confirmation dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false // Dismiss the dialog if clicked outside
            },
            title = {
                Text(text = stringResource(id = R.string.confirm_reset_title))
            },
            text = {
                Text(text = stringResource(id = R.string.confirm_reset_body))
            },
            confirmButton = {
                Button(
                    onClick = {
                        // Reset the timer
                        time = 0L
                        isRunning = false
                        startTime = 0L
                        elapsedTime = 0L
                        editor.clear().apply() // Clear all shared preferences
                        showDialog = false // Dismiss the dialog
                    }
                ) {
                    Text(stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false // Dismiss the dialog
                    }
                ) {
                    Text(stringResource(id = R.string.no))
                }
            }
        )
    }

    LaunchedEffect(isRunning) {
        // Update the timer every second when running
        while (isRunning) {
            delay(1000)
            time = elapsedTime + (System.currentTimeMillis() - startTime)
            checkMilestones(time, milestones, context)
        }
    }
}

fun checkMilestones(time: Long, milestones: List<Badge>, context: Context) {
    val sharedPreferences = context.getSharedPreferences("BadgesPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    milestones.forEach { milestone ->
        val badgeKey = "badge_${milestone.id}"
        val isEarned = sharedPreferences.getBoolean(badgeKey, false)
        if (!isEarned && time >= milestone.milestoneMillis) {
            editor.putBoolean(badgeKey, true)
            editor.apply()
        }
    }
}

@Composable
fun formatTime(timeMillis: Long): String {
    val days = TimeUnit.MILLISECONDS.toDays(timeMillis)
    val hours = TimeUnit.MILLISECONDS.toHours(timeMillis) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeMillis) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis) % 60

    return String.format(Locale.getDefault(), "%02d : %02d : %02d : %02d", days, hours, minutes, seconds)
}