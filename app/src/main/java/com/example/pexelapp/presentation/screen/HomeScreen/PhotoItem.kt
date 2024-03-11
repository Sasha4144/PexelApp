package com.example.pexelapp.presentation.screen.HomeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

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