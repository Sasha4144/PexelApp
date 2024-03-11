package com.example.pexelapp.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelapp.domain.model.PhotoDomain
import com.example.pexelapp.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    val favoritePhotos = MutableStateFlow<List<PhotoDomain>>(listOf())

    init {
        viewModelScope.launch {
            getFavoritePhotos()
        }
    }

    suspend fun getFavoritePhotos() {
        val photos = databaseRepository.getPhotos()
        favoritePhotos.emit(photos)
    }
}