package com.example.pexelapp.presentation.screen.BookmarkScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PhotoItem(
    id: Int,
    author: String,
    url: String,
    onClick: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.BottomCenter

    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick(id.toString())
                },
            model = url,
            contentDescription = "Image",
            contentScale = ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .height(32.dp)
                .background(Color.Black.copy(0.66f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = author,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White

            )
        }
    }
}