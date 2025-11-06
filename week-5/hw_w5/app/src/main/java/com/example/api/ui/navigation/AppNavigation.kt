package com.example.api.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel // <-- Check import này
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.api.ui.screens.detail.TaskDetailScreen
import com.example.api.ui.screens.list.TaskListScreen
import com.example.api.viewmodel.TaskViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // ✅ QUAN TRỌNG: KHỞI TẠO VIEWMODEL 1 LẦN Ở ĐÂY
    val taskViewModel: TaskViewModel = viewModel()

    NavHost(navController = navController, startDestination = ScreenRoute.LIST) {

        composable(ScreenRoute.LIST) {
            TaskListScreen(
                viewModel = taskViewModel, // <-- Truyền nó vào
                onTaskClick = { taskId ->
                    navController.navigate("${ScreenRoute.DETAIL}/$taskId")
                }
            )
        }

        composable(
            route = ScreenRoute.DETAIL_ROUTE,
            arguments = listOf(navArgument(ScreenRoute.DETAIL_ARG_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString(ScreenRoute.DETAIL_ARG_ID)
            TaskDetailScreen(
                viewModel = taskViewModel, // <-- Truyền CÙNG MỘT viewModel vào
                taskId = taskId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}