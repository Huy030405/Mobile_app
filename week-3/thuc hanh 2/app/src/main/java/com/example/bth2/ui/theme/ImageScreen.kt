package com.example.bth2.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bth2.R


@Composable
fun ImageScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    { Image(painter = painterResource(id= R.drawable.avt),
            contentDescription = "In app",
            modifier = Modifier.size(150.dp)
        )
        Text("in app")
    }
}

