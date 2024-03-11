package com.example.pexelapp.presentation.screen.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pexelapp.domain.model.CollectionDomain

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