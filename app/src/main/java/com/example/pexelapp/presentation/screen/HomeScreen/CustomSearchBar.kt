package com.example.pexelapp.presentation.screen.HomeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pexelapp.R
import com.example.pexelapp.presentation.screen.SplashScreen.Splash
import com.example.pexelapp.ui.theme.LightGray
import com.example.pexelapp.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    active: Boolean,
    text: String,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 24.dp, end = 24.dp),
        query = text,
        onQueryChange = onQueryChanged,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChanged,
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = "Search icon"
            )
        },
        trailingIcon = {
            if (active) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Search icon",
                    modifier = Modifier.clickable {
                        if (text.isNotBlank()) {
                            onQueryChanged("")
                        } else {
                            onActiveChanged(false)
                        }
                    }
                )
            }

        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primary,
            inputFieldColors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.tertiary
            )
        )
    ) {

    }
}