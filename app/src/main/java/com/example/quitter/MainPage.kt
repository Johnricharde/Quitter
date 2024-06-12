package com.example.quitter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quitter.ui.theme.QuitterTheme

@Composable
fun MainPage(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    )
    {
        // Time counter
        DisplayTimer(modifier.weight(0.8f))

        // Title and logo
        DisplayLogo(modifier.weight(0.8f))

        // Navigation buttons
        DisplayNavButtons(modifier.weight(1f), navController)
    }
}

@Composable
fun DisplayTimer(modifier: Modifier = Modifier) {
    Surface(
        color = colorResource(id = R.color.gray),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            TimeCounter()
        }
    }
}

@Composable
fun DisplayLogo(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.quitter_all_cap),
                color = colorResource(id = R.color.gray),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
            )
            Image(
                painter = painterResource(id = R.drawable.quitter_logo),
                contentDescription = "Quitter Logo"
            )
        }
    }
}

@Composable
fun DisplayNavButtons(modifier: Modifier, navController: NavHostController) {
    Surface(
        color = colorResource(id = R.color.gray),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Craving button
            CravingButton(url = "https://www.nytimes.com/crosswords", text = stringResource(R.string.craving_btn_txt))
            // Achievements button
            TextButton(onClick = { navController.navigate("achievementsPage") }, text = stringResource(R.string.achievements_btn_txt))
            // Statistics button
            TextButton(onClick = { navController.navigate("statisticsPage") }, text = stringResource(R.string.statistics_btn_txt))
            // Settings button
            TextButton(onClick = { navController.navigate("settingsPage") }, text = stringResource(R.string.settings_btn_text))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPagePreview() {
    QuitterTheme {
        MainPage(navController = rememberNavController())
    }
}