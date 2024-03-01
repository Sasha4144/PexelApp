package com.example.pexelapp.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun BookmarkScreen() {
    Text(
        modifier = Modifier.fillMaxSize().wrapContentHeight(),
        text = "Bookmark screen",
        textAlign = TextAlign.Center
    )
}