package com.example.pexelapp.presentation.di

import com.example.pexelapp.data.api.ApiService
import com.example.pexelapp.data.repository.PhotoRepositoryImpl
import com.example.pexelapp.domain.repository.PhotoRepository
import com.example.pexelapp.utils.Constants.URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(apiService: ApiService): PhotoRepository {
        return PhotoRepositoryImpl(apiService)
    }
}