package com.example.pexelapp.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pexelapp.presentation.navigation.Screen
import com.example.pexelapp.presentation.view.DetailsViewModel
import com.example.pexelapp.ui.theme.Black
import com.example.pexelapp.ui.theme.LightGray
import com.example.pexelapp.ui.theme.Red
import com.example.pexelapp.ui.theme.White
import com.example.pexelapp.R
import com.example.pexelapp.presentation.downloader.PhotoDownloader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navController: NavController,
    id: String?,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val photo by viewModel.selectedPhoto.collectAsStateWithLifecycle()

    when (navController.previousBackStackEntry?.destination?.route) {
        Screen.Home.route -> {
            viewModel.getPhotoFromHomeScreen(id?.toInt())
        }
    }
    val downloader = PhotoDownloader(LocalContext.current)


    Scaffold(
        modifier = Modifier.padding(horizontal = 24.dp),
        topBar = {
            CustomToolBar(
                title = photo?.photographer ?: photo?.alt ?: "",
                hasNavigation = true,
                onNavigationItemClicked = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            DetailsPhoto(url = photo?.src?.original)

            BottomBlock(
                onDownloadClicked = {
                    downloader.downloadFile(photo!!.src.original, photo!!.alt.trim())
                }
            )

        }
    }

}

@Composable
fun CustomToolBar(
    title: String,
    hasNavigation: Boolean,
    onNavigationItemClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (hasNavigation) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(40.dp)
                    .background(LightGray)
            ) {
                IconButton(onClick = onNavigationItemClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Black
                    )
                }
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Black,
        )
    }
}


@Composable
fun BottomBlock(
    onDownloadClicked: () -> Unit
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
    }
}

@Composable
fun DetailsPhoto(
    url: String?
) {
    if (url != null) {
        AsyncImage(
            modifier = Modifier
                .padding(top = 24.dp)
                .clip(RoundedCornerShape(24.dp))
                .fillMaxWidth(),
            model = url,
            contentDescription = "Image",
        )
    }
}