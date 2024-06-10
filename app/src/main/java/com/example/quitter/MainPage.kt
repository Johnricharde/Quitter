package com.example.quitter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quitter.ui.theme.QuitterTheme
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit


@Composable
fun MainPage(navController: NavHostController, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    )
    {
        // Day counter
        Surface(
            color = colorResource(id = R.color.gray),
            shape = RoundedCornerShape(20.dp),
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .weight(0.75f)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                TimeCounter()
            }
        }
        // Title and logo
        Surface(
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .weight(2f)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                DisplayLogo()
            }
        }
        // Navigation buttons
        Surface(
            color = colorResource(id = R.color.gray),
            shape = RoundedCornerShape(20.dp),
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .weight(2f)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Craving button
                FilledButton(onClick = { /*TODO*/ }, text = "Craving")
                // Achievements button
                TextButton(onClick = { navController.navigate("achievementsPage") }, text = "Achievements")
                // Statistics button
                TextButton(onClick = { navController.navigate("statisticsPage") }, text = "Statistics")
                // Settings button
                TextButton(onClick = { navController.navigate("settingsPage") }, text = "Settings")
            }
        }
    }
}

@Composable
fun DisplayLogo() {
    Text(
        text = stringResource(R.string.quitter_all_cap),
        color = colorResource(id = R.color.gray),
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
    )
    // Logo
    Image(
        painter = painterResource(id = R.drawable.quitter_logo),
        contentDescription = "Quitter Logo"
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun TimeCounter() {
    var time by remember { mutableLongStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }
    var startTime by remember { mutableLongStateOf(0L) }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
    Row {
        Button(
            onClick = {
                if (isRunning) {
                    isRunning = false
                } else {
                    startTime = System.currentTimeMillis()
                    isRunning = true
                    keyboardController?.hide()
                }
            }
        ) {
            Text(text = if (isRunning) "Stop" else "Start")
        }
        Button(
            onClick = {
                time = 0
                isRunning = false
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

    return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds)
}

@Composable
fun FilledButton(onClick: () -> Unit, text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.gray)
        )
    ) {
        Text(
            text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(8.dp)
        )
    }
}

@Composable
fun TextButton(onClick: () -> Unit, text: String) {
    androidx.compose.material3.TextButton(
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = colorResource(id = R.color.white),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPagePreview() {
    QuitterTheme {
        MainPage(navController = rememberNavController())
    }
}