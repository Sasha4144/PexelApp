package com.example.pexelapp.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelapp.domain.model.CollectionDomain
import com.example.pexelapp.domain.model.PhotoDomain
import com.example.pexelapp.domain.repository.WebRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val webRepository: WebRepository
) : ViewModel() {
    val photos = MutableStateFlow<List<PhotoDomain>>(listOf())
    val featuredState = MutableStateFlow<List<CollectionDomain>>(listOf())
    val searchText = MutableStateFlow<String>("")
    val selectedFeaturedCollectionId = MutableStateFlow<String>("")

    init {
        viewModelScope.launch {
            val featuredCollections = webRepository.getFeaturedCollectionList(
                page = 1,
                per_page = 7
            )
            featuredState.emit(featuredCollections)

            val curatedPhotos = webRepository.getCuratedPhotosList(
                per_page = 30,
                page = 1
            )
            photos.emit(curatedPhotos)
        }
    }

    fun changeSearchText(text: String) {
        searchText.value = text
        selectedFeaturedCollectionId.value = ""
    }

    fun changeSelectedId(id: String) {
        selectedFeaturedCollectionId.value = id
    }

    suspend fun searchPhotos(text: String) {
        val newPhotosList = webRepository.getPhotosList(
            query = text,
            page = 1,
            per_page = 30
        )
        photos.emit(newPhotosList)
    }


}