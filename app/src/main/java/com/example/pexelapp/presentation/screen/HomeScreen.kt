package com.example.pexelapp.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pexelapp.domain.model.CollectionDomain
import com.example.pexelapp.domain.model.PhotoDomain
import com.example.pexelapp.presentation.view.HomeViewModel
import com.example.pexelapp.ui.theme.Black
import com.example.pexelapp.ui.theme.LightGray
import com.example.pexelapp.ui.theme.Red
import com.example.pexelapp.ui.theme.White
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

@Composable
fun PhotosBlock(
    photos: List<PhotoDomain>,
    onPhotoClicked: (String) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 64.dp),
        columns = StaggeredGridCells.Fixed(2),
    ) {
        items(photos) {
            PhotoItem(
                url = it.src.medium,
                onClick = onPhotoClicked,
                id = it.id
            )
        }
    }
}

@Composable
fun PhotoItem(
    url: String,
    onClick: (String) -> Unit = {},
    id: Int
) {
    AsyncImage(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(24.dp))
            .clickable {
                onClick(id.toString())
            },
        model = url,
        contentDescription = "Image",
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun FeaturedBlock(
    selectedId: String,
    items: List<CollectionDomain>,
    changeSearchText: (String) -> Unit,
    changeSelectedId: (String) -> Unit,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(11.dp),

        ) {
        items(items) { it ->
            FeaturedItem(
                id = it.id,
                title = it.title,
                onClick = { id ->
                    changeSearchText(it.title)
                    changeSelectedId(it.id)
                },
                selected = it.id == selectedId
            )
        }

    }
}

@Composable
fun FeaturedItem(
    id: String,
    title: String,
    selected: Boolean,
    onClick: (String) -> Unit
) {
    val background = if (selected) Red else LightGray
    val textColor = if (selected) White else Black

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(background)
            .clickable { onClick(id) },
    ) {

        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            text = title,
            color = textColor,
            fontSize = 14.sp
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    active: Boolean,
    text: String,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 24.dp, end = 24.dp),
        query = text,
        onQueryChange = onQueryChanged,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChanged,
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = Red,
                contentDescription = "Search icon"
            )
        },
        trailingIcon = {
            if (active) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Search icon",
                    modifier = Modifier.clickable {
                        if (text.isNotBlank()) {
                            onQueryChanged("")
                        } else {
                            onActiveChanged(false)
                        }
                    }
                )
            }

        },
        colors = SearchBarDefaults.colors(
            containerColor = LightGray,
            inputFieldColors = TextFieldDefaults.colors(
                cursorColor = Red
            )
        )
    ) {

    }
}