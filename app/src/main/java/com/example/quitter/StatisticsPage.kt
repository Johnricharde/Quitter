package com.example.quitter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quitter.ui.theme.QuitterTheme

@Composable
fun StatisticsPage(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .weight(4f)
        ) {
            Row(modifier = modifier.padding(bottom = 16.dp)) {
                Text("You've gone ")
                Text("31 days", fontWeight = FontWeight.Bold)
                Text(" without smoking!")
            }

            Row(modifier = modifier.padding(bottom = 16.dp)) {
                Text("You've saved ")
                Text("1200 kr", fontWeight = FontWeight.Bold)
                Text("!")
            }

            Row(modifier = modifier.padding(bottom = 16.dp)) {
                Text("You've increased your lifespan by ")
                Text("1 year", fontWeight = FontWeight.Bold)
                Text("!")
            }
        }
        GoBackButton(modifier.weight(1f), navController)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StatisticsPagePreview() {
    QuitterTheme {
        StatisticsPage(navController = rememberNavController())
    }
}