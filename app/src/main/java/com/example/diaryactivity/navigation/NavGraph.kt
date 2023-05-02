package com.example.diaryactivity.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diaryactivity.ui.HomeScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController , startDestination = Routes.HOME.name , builder = {
        composable(route = Routes.HOME.name) {
            HomeScreen()
        }
    })
}

enum class Routes {
    HOME
}