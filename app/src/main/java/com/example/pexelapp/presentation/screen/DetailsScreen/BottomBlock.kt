package com.example.pexelapp.presentation.screen.DetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pexelapp.R
import com.example.pexelapp.ui.theme.LightDarkGray
import com.example.pexelapp.ui.theme.LightGray
import com.example.pexelapp.ui.theme.Red
import com.example.pexelapp.ui.theme.White

@Composable
fun BottomBlock(
    isPhotoFavorite: Boolean,
    onDownloadClicked: () -> Unit,
    onFavoriteClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .background(White)
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .width((screenWidth - 48.dp) / 2)
                .background(LightGray)
                .clickable { onDownloadClicked() },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Red),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.download),
                    contentDescription = "Download button",
                    tint = White
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Download",
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(LightGray)
                .clickable { onFavoriteClicked() },
            contentAlignment = Alignment.Center
        ) {

            val painter =
                if (isPhotoFavorite) R.drawable.bookmark_button_active else R.drawable.bookmark_button_inactive
            val contentColor =
                if (isPhotoFavorite) Red else LightDarkGray

            Icon(
                painter = painterResource(id = painter),
                contentDescription = "Bookmark button",
                tint = contentColor
            )
        }
    }
}

@Composable
@Preview
fun BottomBlockPreview() {
    BottomBlock(isPhotoFavorite = true, onDownloadClicked = { /*TODO*/ }) {

    }
}