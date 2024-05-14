package com.example.quitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quitter.ui.theme.QuitterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuitterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun MainPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    )
    {
        // Header //////////////////////////////////////////////////////////////////////////
        Surface(
            color = colorResource(id = R.color.gray),
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                Text(text = "You've gone", color = colorResource(id = R.color.white))
                Text(text = "31 days", color = colorResource(id = R.color.white))
                Text(text = "without smoking!", color = colorResource(id = R.color.white))
            }
        }
        // Body ////////////////////////////////////////////////////////////////////////////
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
                Text(
                    text = "QUITTER",
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
        // Footer //////////////////////////////////////////////////////////////////////////
        Surface(
            color = colorResource(id = R.color.gray),
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .weight(2f)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = { /* Do something! */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.white),
                        contentColor = colorResource(id = R.color.gray)
                    )
                ) {
                    Text("I have a craving!")
                }
                Text(text = "Achievements", color = colorResource(id = R.color.white))
                Text(text = "Statistics", color = colorResource(id = R.color.white))
                Text(text = "Settings", color = colorResource(id = R.color.white))
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPagePreview() {
    QuitterTheme {
        MainPage()
    }
}