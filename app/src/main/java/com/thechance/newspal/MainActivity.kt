package com.thechance.newspal

import BottomBarUi
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.thechance.newspal.theme.NewsPalTheme
import com.thechance.newspal.ui.navigation.MainNavGraph

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navController = rememberNavController()

            NewsPalTheme {
                Scaffold(
                    bottomBar = {
                        BottomBarUi(navController)
                    },
                    content = {
                        Box(modifier = Modifier.padding(bottom = 56.dp)) {
                            MainNavGraph(navController = navController)
                        }
                    }
                )
            }
        }
    }
}

