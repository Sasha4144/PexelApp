package com.example.pexelapp.presentation.screen.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pexelapp.ui.theme.Black
import com.example.pexelapp.ui.theme.LightGray
import com.example.pexelapp.ui.theme.Red
import com.example.pexelapp.ui.theme.White

@Composable
fun FeaturedItem(
    id: String,
    title: String,
    selected: Boolean,
    onClick: (String) -> Unit
) {
    val background = if (selected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
    val textColor = if (selected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground

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