package com.example.pexelapp.presentation.screen.BookmarkScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pexelapp.presentation.navigation.Screen
import com.example.pexelapp.presentation.screen.DetailsScreen.CustomToolBar
import com.example.pexelapp.presentation.view.BookmarkViewModel

@Composable
fun BookmarkScreen(
    navController: NavController,
    viewModel: BookmarkViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getFavoritePhotos()
    }

    val favouritePhotos by viewModel.favoritePhotos.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CustomToolBar(title = "Bookmarks", hasNavigation = false)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 64.dp),
        ) {
            if (favouritePhotos.isNotEmpty()) {
                FavoritePhotosBlock(
                    photos = favouritePhotos,
                    onPhotoClicked = {
                        navController.navigate("details/$it")
                    }
                )
            } else {
                EmptyScreen(
                    message = "You haven\'t saved anything yet",
                    onExploreClicked = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}