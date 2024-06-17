package com.example.quitter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quitter.ui.theme.QuitterTheme

@Composable
fun SettingsPage(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel = viewModel()) {
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxSize(0.5f)
            ) {
                Text(
                    text = stringResource(id = R.string.dark_mode_txt),
                )
                ThemeSwitcher(
                    darkTheme = themeViewModel.isDarkTheme,
                    onThemeChange = { themeViewModel.toggleTheme() }
                )
            }
        }
        GoBackButton(modifier.weight(1f), navController)
    }
}

@Composable
fun ThemeSwitcher(darkTheme: Boolean, onThemeChange: () -> Unit) {
    Switch(
        checked = darkTheme,
        onCheckedChange = { onThemeChange() },
        colors = SwitchDefaults.colors(
            checkedTrackColor = MaterialTheme.colorScheme.background,
            checkedThumbColor = MaterialTheme.colorScheme.primary,
            uncheckedTrackColor = MaterialTheme.colorScheme.background,
            uncheckedThumbColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsPagePreview() {
    QuitterTheme {
        SettingsPage(navController = rememberNavController())
    }
}