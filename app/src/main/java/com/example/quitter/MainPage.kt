package com.example.quitter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.quitter.ui.theme.QuitterTheme
import androidx.navigation.NavHostController


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
                DayCounter()
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

@Composable
private fun DayCounter() {
    Text(
        text = stringResource(R.string.header_txt_1),
        color = colorResource(id = R.color.white),
        fontSize = 20.sp,
    )
    Text(
        // Need to show the count here
        text = stringResource(R.string.header_txt_2),
        color = colorResource(id = R.color.white),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    )
    Text(
        text = stringResource(R.string.header_txt_3),
        color = colorResource(id = R.color.white),
        fontSize = 20.sp,
    )
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