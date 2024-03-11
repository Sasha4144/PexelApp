package com.example.pexelapp.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pexelapp.ui.theme.LightDarkGray
import com.example.pexelapp.ui.theme.Red

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(Screen.Home, Screen.Bookmark)

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val color = if (selected) Red else LightDarkGray
            Box(
                modifier = Modifier.size(64.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .clickable(onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        }),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = if (selected) screen.id_icon_active!! else screen.id_icon_inactive!!),
                        contentDescription = "icon",
                        tint = color
                    )
                }

                this@Row.AnimatedVisibility(visible = selected) {
                    Divider(
                        modifier = Modifier.size(24.dp, 2.dp),
                        color = color
                    )
                }
            }
        }
    }
}