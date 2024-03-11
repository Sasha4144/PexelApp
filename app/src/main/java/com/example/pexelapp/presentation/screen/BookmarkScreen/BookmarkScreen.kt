package com.example.pexelapp.presentation.screen.BookmarkScreen

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pexelapp.R
import com.example.pexelapp.presentation.navigation.Screen
import com.example.pexelapp.presentation.screen.DetailsScreen.CustomToolBar
import com.example.pexelapp.presentation.screen.EmptyScreen
import com.example.pexelapp.presentation.screen.ProgressBar
import com.example.pexelapp.presentation.view.BookmarkViewModel

@Composable
fun BookmarkScreen(
    navController: NavController,
    viewModel: BookmarkViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getFavoritePhotos()
    }
    val window = (LocalContext.current as Activity).window
    window.statusBarColor = MaterialTheme.colorScheme.background.toArgb()
    window.navigationBarColor = MaterialTheme.colorScheme.background.toArgb()

    val favoritePhotos by viewModel.favoritePhotos.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CustomToolBar(title = stringResource(id = R.string.bookmarks), hasNavigation = false)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 64.dp),
        ) {

            ProgressBar(loading = favoritePhotos.isEmpty())

            if (favoritePhotos.isNotEmpty()) {
                FavoritePhotosBlock(
                    photos = favoritePhotos,
                    onPhotoClicked = {
                        navController.navigate("details/$it")
                    }
                )
            } else {
                EmptyScreen(
                    message = stringResource(id = R.string.no_bookmarks),
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