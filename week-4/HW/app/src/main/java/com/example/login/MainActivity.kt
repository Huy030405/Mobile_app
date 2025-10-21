package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.ui.theme.ConfirmScreen
import com.example.login.ui.theme.CreatePasswordScreen
import com.example.login.ui.theme.ForgotPasswordScreen
//import com.example.login.ui.theme.ForgotEmailScreen
import com.example.login.ui.theme.VerifyScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "forgot_password"
                ) {
                    composable("forgot_password") {
                        ForgotPasswordScreen(navController)
                    }
                    composable("verify/{email}") { backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email") ?: ""
                        VerifyScreen(navController, email)
                    }
                    composable("create_password/{email}") { backStack ->
                        val email = backStack.arguments?.getString("email") ?: ""
                        CreatePasswordScreen(navController, email)
                    }

                    // Màn hình 4: Xác nhận thông tin, truyền email + password
                    composable("confirm/{email}/{password}") { backStack ->
                        val email = backStack.arguments?.getString("email") ?: ""
                        val password = backStack.arguments?.getString("password") ?: ""
                        ConfirmScreen(navController, email, password)
                    }

                }
            }
        }
    }
}


