package com.example.bth2.ui.navigation

import ComponentsListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bth2.ui.screen.*
import com.example.bth2.ui.theme.ImageScreen
import com.example.bth2.ui.theme.RowLayoutScreen
import com.example.bth2.ui.theme.TextDetailScreen
import com.example.bth2.ui.theme.TextFieldScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {

        composable("welcome") { WelcomeScreen(onNext = { navController.navigate("components_list") }) }

        composable("components_list") { ComponentsListScreen(navController) }

        composable("text_detail") { TextDetailScreen(navController) }

        composable("image_screen") { ImageScreen(navController) }

        composable("textfield_screen") { TextFieldScreen(navController) }

        composable("rowlayout_screen") { RowLayoutScreen(navController) }
    }
}

