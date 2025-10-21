package com.example.bth2.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TextDetailScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("The ", fontSize = 20.sp)
        Text("quick ", color = Color.Gray, fontSize = 20.sp)
        Text("Brown ", color = Color(0xFFB87333), fontSize = 20.sp)
        Text("fox j", fontSize = 20.sp)
        Text("umps ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("over ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("the lazy dog.", fontSize = 20.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
    }
}
