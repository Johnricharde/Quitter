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
            text = if (isRunning) "Stop" else "Start"
        )
        FilledButton(
            onClick = {
                // Show confirmation dialog for reset
                showDialog = true
            },
            text = "Reset"
        )
    }

    // Reset confirmation dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false // Dismiss the dialog if clicked outside
            },
            title = {
                Text(text = "Reset Timer")
            },
            text = {
                Text(text = "Are you sure you want to reset the timer?")
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
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false // Dismiss the dialog
                    }
                ) {
                    Text("No")
                }
            }
        )
    }

    LaunchedEffect(isRunning) {
        // Update the timer every second when running
        while (isRunning) {
            delay(1000)
            time = elapsedTime + (System.currentTimeMillis() - startTime)
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