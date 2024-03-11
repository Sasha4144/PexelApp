package com.example.pexelapp.presentation.screen.BookmarkScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pexelapp.ui.theme.Black
import com.example.pexelapp.ui.theme.Red

@Composable
fun EmptyScreen(
    message: String,
    onExploreClicked: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = message,
                color = Black,
                fontSize = 14.sp
            )

            Text(text = "Explore", color = Red, fontSize = 18.sp, modifier = Modifier.clickable {
                onExploreClicked()
            })
        }
    }
}

@Composable
@Preview
fun EmptyScreenPreview() {
    EmptyScreen(message = "message") {

    }
}