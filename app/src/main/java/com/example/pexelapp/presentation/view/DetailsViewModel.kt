package com.example.pexelapp.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelapp.data.model.Photo
import com.example.pexelapp.data.model.asPhoto
import com.example.pexelapp.domain.model.PhotoDomain
import com.example.pexelapp.domain.repository.DatabaseRepository
import com.example.pexelapp.domain.repository.WebRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val webRepository: WebRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    val selectedPhoto = MutableStateFlow<PhotoDomain?>(null)
    val isPhotoFavorite = MutableStateFlow<Boolean>(false)

    fun getPhotoFromHomeScreen(id: Int?) {
        if (id != null) {
            viewModelScope.launch {
                val photo = webRepository.getPhotoById(id)
                selectedPhoto.emit(photo)
            }
        }
    }

    fun getPhotoFromBookmarkScreen(id: Int?) {
        if (id != null) {
            viewModelScope.launch {
                val photo = databaseRepository.getPhotoById(id)
                selectedPhoto.emit(photo)
                countPhotosById(id)
            }
        }
    }

    fun onFavoriteButtonClicked(photoDomain: PhotoDomain) {
        val photo = photoDomain.asPhoto()
        viewModelScope.launch {
            if (isPhotoFavorite.value) {
                removeFromFavourites(photo)
                isPhotoFavorite.value = false
            } else {
                addToFavorites(photo)
                isPhotoFavorite.value = true
            }
            countPhotosById(photo.id)
        }
    }

    private suspend fun addToFavorites(photo: Photo) {
        viewModelScope.launch {
            databaseRepository.addPhoto(photo)
        }

    }

    private suspend fun removeFromFavourites(photo: Photo) {
        viewModelScope.launch {
            databaseRepository.removePhoto(photo)
        }
    }

    suspend fun countPhotosById(id: Int) {
        viewModelScope.launch {
            val count = databaseRepository.countPhotosById(id)
            isPhotoFavorite.value = count != 0
        }
    }
}