package com.example.pexelapp.presentation.screen.DetailsScreen

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.pexelapp.presentation.downloader.PhotoDownloader
import com.example.pexelapp.presentation.navigation.Screen
import com.example.pexelapp.presentation.screen.EmptyScreen
import com.example.pexelapp.presentation.view.DetailsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navController: NavController,
    id: String?,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val window = (LocalContext.current as Activity).window
    window.statusBarColor = MaterialTheme.colorScheme.background.toArgb()
    window.navigationBarColor = MaterialTheme.colorScheme.background.toArgb()
    val photo by viewModel.selectedPhoto.collectAsStateWithLifecycle()
    val isPhotoFavorite by viewModel.isPhotoFavorite.collectAsStateWithLifecycle()

    when (navController.previousBackStackEntry?.destination?.route) {
        Screen.Home.route -> {
            viewModel.getPhotoFromHomeScreen(id?.toInt())
        }

        Screen.Bookmark.route -> {
            viewModel.getPhotoFromBookmarkScreen(id?.toInt())
        }
    }
    val downloader = PhotoDownloader(LocalContext.current)

    Scaffold(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        topBar = {
            CustomToolBar(
                title = photo?.photographer ?: "",
                hasNavigation = true,
                onNavigationItemClicked = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            if (photo != null) {
                DetailsPhoto(url = photo?.src?.original)

                BottomBlock(
                    isPhotoFavorite = isPhotoFavorite,
                    onDownloadClicked = {
                        downloader.downloadFile(photo!!.src.original, photo!!.alt.trim())
                    },
                    onFavoriteClicked = {
                        viewModel.onFavoriteButtonClicked(photo!!)
                    }
                )
            } else {
                EmptyScreen(
                    message = stringResource(id = R.string.image_not_found),
                    onExploreClicked = { navController.popBackStack() }
                )
            }
        }
    }
}