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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pexelapp.R

@Composable
fun BottomBlock(
    isPhotoFavorite: Boolean,
    onDownloadClicked: () -> Unit,
    onFavoriteClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .background(MaterialTheme.colorScheme.background)
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
                .background(MaterialTheme.colorScheme.primary)
                .clickable { onDownloadClicked() },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.download),
                    contentDescription = "Download button",
                    tint = MaterialTheme.colorScheme.background
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.download),
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.primary)
                .clickable { onFavoriteClicked() },
            contentAlignment = Alignment.Center
        ) {

            val painter =
                if (isPhotoFavorite) R.drawable.bookmark_button_active else R.drawable.bookmark_button_inactive
            val contentColor =
                if (isPhotoFavorite) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary

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