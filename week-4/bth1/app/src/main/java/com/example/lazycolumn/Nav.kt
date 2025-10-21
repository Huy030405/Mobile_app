package com.example.lazycolumn

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lazycolumn.ui.screens.RootScreen
import com.example.navigationpractice.ui.screens.*

@Composable
fun Nav(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "root") {
        composable("root") { RootScreen(navController) }
        composable("list") { ListScreen(navController) }
        composable("detail/{quote}") { backStackEntry ->
            val quote = backStackEntry.arguments?.getString("quote") ?: ""
            DetailScreen(navController, quote)
        }
    }
}
