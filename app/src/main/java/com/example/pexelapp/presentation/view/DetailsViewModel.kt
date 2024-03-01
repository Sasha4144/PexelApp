package com.example.pexelapp.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelapp.domain.model.PhotoDomain
import com.example.pexelapp.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    val selectedPhoto = MutableStateFlow<PhotoDomain?>(null)

    fun getPhotoFromHomeScreen(id: Int?) {
        if(id != null) {
            viewModelScope.launch {
                val photo = photoRepository.getPhotoById(id)
                selectedPhoto.emit(photo)
            }
        }

    }
}