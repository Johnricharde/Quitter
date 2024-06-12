package com.example.quitter

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Locale
import java.util.concurrent.TimeUnit

@Composable
fun TimeCounter() {
    var time by remember { mutableLongStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }
    var startTime by remember { mutableLongStateOf(0L) }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("TimerPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    LaunchedEffect(Unit) {
        startTime = sharedPreferences.getLong("startTime", 0L)
        isRunning = sharedPreferences.getBoolean("isRunning", false)
        if (isRunning) {
            time = System.currentTimeMillis() - startTime
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Text(
            text = formatTime(timeMillis = time),
            color = colorResource(id = R.color.white),
            fontSize = 40.sp)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                if (isRunning) {
                    isRunning = false
                    editor.putBoolean("isRunning", false)
                    editor.apply()
                } else {
                    startTime = System.currentTimeMillis()
                    isRunning = true
                    editor.putLong("startTime", startTime)
                    editor.putBoolean("isRunning", true)
                    editor.apply()
                }
            }
        ) {
            Text(text = if (isRunning) "Stop" else "Start")
        }
        Button(
            onClick = {
                time = 0
                isRunning = false
                editor.putLong("startTime", 0L)
                editor.putBoolean("isRunning", false)
                editor.apply()
            }
        ) {
            Text(text = "Reset")
        }
    }
    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000)
            time = System.currentTimeMillis() - startTime
        }
    }
}

@Composable
fun formatTime(timeMillis: Long): String {
    val days = TimeUnit.MILLISECONDS.toDays(timeMillis)
    val hours = TimeUnit.MILLISECONDS.toHours(timeMillis)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeMillis) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis) % 60

    return String.format(Locale.getDefault(), "%02d:%02d:%02d:%02d", days, hours, minutes, seconds)
}