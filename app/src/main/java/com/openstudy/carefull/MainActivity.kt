package com.openstudy.carefull

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.openstudy.carefull.screen.Splash
import com.openstudy.carefull.ui.theme.CarefullTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarefullTheme {
                val navController = rememberNavController()
                Splash(navController)
            }
        }
    }
}