package com.example.bth2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bth2.ui.navigation.AppNavigation
import com.example.bth2.ui.screen.WelcomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            WelcomeScreen()
            AppNavigation()
        }
    }
}