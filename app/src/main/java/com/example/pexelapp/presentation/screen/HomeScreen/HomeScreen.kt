package com.example.pexelapp.presentation.screen.HomeScreen

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pexelapp.R
import com.example.pexelapp.presentation.navigation.Screen
import com.example.pexelapp.presentation.screen.EmptyScreen
import com.example.pexelapp.presentation.screen.ProgressBar
import com.example.pexelapp.presentation.view.HomeViewModel
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val window = (LocalContext.current as Activity).window
    window.statusBarColor = MaterialTheme.colorScheme.background.toArgb()
    window.navigationBarColor = MaterialTheme.colorScheme.background.toArgb()
    val coroutineScope = rememberCoroutineScope()

    val photos by viewModel.photos.collectAsStateWithLifecycle()
    val featuredList by viewModel.featuredState.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val selectedFeaturedCollectionId by viewModel.selectedFeaturedCollectionId.collectAsStateWithLifecycle()

    var active by remember { mutableStateOf(false) }

    Column {

        CustomSearchBar(
            text = searchText,
            active = active,
            onQueryChanged = {
                viewModel.changeSearchText(it)
            },
            onSearch = {
                active = false
                coroutineScope.launch {
                    viewModel.searchPhotos(it)
                }
            },
            onActiveChanged = {
                active = it
            }
        )

        FeaturedBlock(
            selectedId = selectedFeaturedCollectionId,
            items = featuredList,
            changeSearchText = {
                viewModel.changeSearchText(it)
                coroutineScope.launch {
                    viewModel.searchPhotos(it)
                }
            },
            changeSelectedId = {
                viewModel.changeSelectedId(it)
            }
        )
        
        ProgressBar(loading = photos.isEmpty())
        
        if(photos.isNotEmpty()) {
            PhotosBlock(photos = photos,
                onPhotoClicked = {
                    navController.navigate("details/$it")
                })
        } else {
            EmptyScreen(
                message = stringResource(id = R.string.no_results),
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