package com.example.navigationpractice.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ListScreen(navController: NavController) {
    val quotes = List(100000) { i -> "${i + 1} | The only way to do great work is to love what you do." }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "LazyColumn", style = MaterialTheme.typography.headlineSmall)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(quotes) { quote ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { navController.navigate("detail/$quote") },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(
                        text = quote,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
