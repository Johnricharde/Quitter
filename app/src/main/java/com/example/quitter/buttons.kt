package com.example.quitter

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun CravingButton(url: String, text: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.gray)
        ),
        modifier = modifier
    ) {
        Text(
            text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
    }
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

@Composable
fun GoBackButton(modifier: Modifier = Modifier, navController: NavHostController) {
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
            // Go back button
            FilledButton(onClick = { navController.popBackStack() }, text = "Go back")
        }
    }
}