package com.example.quitter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quitter.ui.theme.QuitterTheme

@Composable
fun SettingsPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .weight(4f)
        ) {
            Row {
                Text(text = "Setting")
                SwitchMinimal()
            }
            Row {
                Text(text = "Setting")
                SwitchMinimal()
            }
            Row {
                Text(text = "Setting")
                SwitchMinimal()
            }
            Row {
                Text(text = "Setting")
                SwitchMinimal()
            }
            Row {
                Text(text = "Setting")
                SwitchMinimal()
            }
        }
        // Go back button
        Surface(
            color = colorResource(id = R.color.gray),
            shape = RoundedCornerShape(20.dp),
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Go back button
                FilledButton(onClick = { /*TODO*/ }, text = "Go back")
            }
        }
    }
}


@Composable
fun SwitchMinimal() {
    Switch(
        checked = true,
        onCheckedChange = { /*TODO*/ },
        modifier = Modifier
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsPagePreview() {
    QuitterTheme {
        SettingsPage()
    }
}