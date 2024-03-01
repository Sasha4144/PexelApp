package com.example.pexelapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pexelapp.presentation.screen.AnimatedSplashScreen
import com.example.pexelapp.presentation.screen.BookmarkScreen
import com.example.pexelapp.presentation.screen.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Bookmark.route) {
            BookmarkScreen()
        }
    }
}