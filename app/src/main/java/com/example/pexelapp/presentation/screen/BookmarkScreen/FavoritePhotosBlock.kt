package com.example.pexelapp.presentation.screen.BookmarkScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pexelapp.domain.model.PhotoDomain

@Composable
fun FavoritePhotosBlock(
    photos: List<PhotoDomain>,
    onPhotoClicked: (String) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 64.dp),
        columns = StaggeredGridCells.Fixed(2),
    ) {
        items(photos) {
            PhotoItem(
                url = it.src.medium,
                author = it.photographer,
                onClick = onPhotoClicked,
                id = it.id
            )
        }
    }
}