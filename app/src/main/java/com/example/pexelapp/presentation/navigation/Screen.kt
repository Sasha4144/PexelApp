package com.example.pexelapp.presentation.navigation

import com.example.pexelapp.R

sealed class Screen(val route: String,
                    val title: String,
                    val id_icon_active: Int? = null,
                    val id_icon_inactive: Int? = null
) {
    object Splash : Screen("splash_screen", "splash")
    object Home : Screen("home_screen",
        "home"
    )
    object Bookmark : Screen("bookmark_screen",
        "bookmark"
    )
}