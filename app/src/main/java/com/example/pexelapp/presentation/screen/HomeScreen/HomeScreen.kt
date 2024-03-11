package com.example.pexelapp.presentation.screen.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pexelapp.presentation.view.HomeViewModel
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
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

        PhotosBlock(photos = photos,
            onPhotoClicked = {
                navController.navigate("details/$it")
            })
    }
}