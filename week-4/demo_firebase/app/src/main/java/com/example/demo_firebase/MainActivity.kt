package com.example.demo_firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demo_firebase.ui.LoginScreen
import com.example.demo_firebase.ui.ProfileScreen
import com.example.demo_firebase.ui.theme.Demo_firebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demo_firebaseTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController, this@MainActivity) }
                    composable("profile/{name}/{email}/{photoUrl}") { backStackEntry ->
                        ProfileScreen(
                            name = backStackEntry.arguments?.getString("name") ?: "",
                            email = backStackEntry.arguments?.getString("email") ?: "",
                            photoUrl = backStackEntry.arguments?.getString("photoUrl") ?: ""
                        )
                    }
                }
            }
        }
    }
}
